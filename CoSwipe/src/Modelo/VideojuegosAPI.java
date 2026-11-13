package Modelo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;
import java.util.regex.*;

public class VideojuegosAPI {

    private static final String ACCESS_TOKEN = "qvnzuy8gww1porjquz8pkdcg06his8";
    private static final String CLIENT_ID = "el9bel95v3p804885xb3ob8dyyydzd";
    private static final String BASE = "https://api.igdb.com/v4/";

    // ---- Ajustes ----
    public static final int ANCHO_ASCII = 80;        // ancho en caracteres
    public static final boolean ASCII_COLOR = true;  // false = blanco y negro
    public static final boolean MOSTRAR_VENTANA = false; // true = abre también la ventana

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String consulta(String endpoint, String query) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE + endpoint))
                .header("Client-ID", CLIENT_ID)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error " + response.statusCode() + ": " + response.body());
        }
        return response.body();
    }

    public static List<String> objetos(String json) {
        List<String> lista = new ArrayList<>();
        int depth = 0, start = -1;
        boolean enString = false, escape = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (enString) {
                if (escape) escape = false;
                else if (c == '\\') escape = true;
                else if (c == '"') enString = false;
                continue;
            }
            if (c == '"') enString = true;
            else if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) lista.add(json.substring(start, i + 1));
            }
        }
        return lista;
    }

    public static String campoTexto(String obj, String campo) {
        Matcher m = Pattern.compile("\"" + campo + "\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"").matcher(obj);
        return m.find() ? m.group(1) : null;
    }

    public static String campoNumero(String obj, String campo) {
        Matcher m = Pattern.compile("\"" + campo + "\"\\s*:\\s*(-?[\\d.]+)").matcher(obj);
        return m.find() ? m.group(1) : null;
    }

    // Convierte una imagen en texto ASCII
    public static String aAscii(BufferedImage original, int ancho, boolean color) {
        int alto = (int) (ancho * (double) original.getHeight() / original.getWidth() * 0.5);

        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(original, 0, 0, ancho, alto, null);
        g2.dispose();

        String rampa = " .:-=+*#%@";
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int gr = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                if (color) {
                    sb.append("\u001b[38;2;").append(r).append(';').append(gr)
                      .append(';').append(b).append("m█");
                } else {
                    double brillo = (0.299 * r + 0.587 * gr + 0.114 * b) / 255.0;
                    sb.append(rampa.charAt((int) (brillo * (rampa.length() - 1))));
                }
            }
            if (color) sb.append("\u001b[0m");
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void mostrarVentana(String genero, List<String> titulos, List<BufferedImage> imagenes) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Top 5 - " + genero);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(1, titulos.size(), 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            for (int i = 0; i < titulos.size(); i++) {
                JPanel item = new JPanel(new BorderLayout(0, 5));

                JLabel imagen = new JLabel("Sin carátula", SwingConstants.CENTER);
                if (imagenes.get(i) != null) {
                    imagen.setText(null);
                    imagen.setIcon(new ImageIcon(imagenes.get(i)));
                }

                JLabel titulo = new JLabel(
                        "<html><div style='text-align:center;width:200px'>" + titulos.get(i) + "</div></html>",
                        SwingConstants.CENTER);

                item.add(imagen, BorderLayout.CENTER);
                item.add(titulo, BorderLayout.SOUTH);
                panel.add(item);
            }

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}