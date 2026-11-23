package modelo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideojuegosAPI {

    private static final String ACCESS_TOKEN = "qvnzuy8gww1porjquz8pkdcg06his8";
    private static final String CLIENT_ID = "el9bel95v3p804885xb3ob8dyyydzd";
    private static final String BASE = "https://api.igdb.com/v4/";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String consulta(String endpoint, String query) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE + endpoint))
                .header("Client-ID", CLIENT_ID)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String[] obtenerJuegoAleatorio(String genero) throws Exception {
        // Mapeamos el género a los IDs que usa la API de IGDB
        int idGenero = 12; 
        if (genero.equals("Shooter")) idGenero = 5;
        else if (genero.equals("RPG")) idGenero = 12;
        else if (genero.equals("Estrategia")) idGenero = 15;
        else if (genero.equals("Deportes")) idGenero = 14;
        else if (genero.equals("Aventura")) idGenero = 31;
        else if (genero.equals("Acción")) idGenero = 4;

        int offsetAleatorio = (int)(Math.random() * 20);

        // Le pedimos a la API que nos devuelva el juego con sus datos
        String query = "fields name, summary, rating, cover.url, platforms.name; "
                + "where genres = " + idGenero + " & rating > 70 & cover != null; "
                + "limit 1; offset " + offsetAleatorio + ";";

        String json = consulta("games", query);
        
        String nombre = "Desconocido";
        String sinopsis = "Sin descripción.";
        String valoracion = "N/A";
        String imagen = "";

        // Extraemos el nombre del juego buscando de forma exacta en el JSON
        Matcher mNombre = Pattern.compile("^\\s*\\{\\s*\"id\"\\s*:\\s*\\d+\\s*,\\s*\"name\"\\s*:\\s*\"([^\"]+)\"").matcher(json);
        if (mNombre.find()) {
            nombre = mNombre.group(1);
        } else {
            // Si el JSON viene en otro orden, buscamos la primera aparición de "name"
            Matcher mAlt = Pattern.compile("\"name\"\\s*:\\s*\"([^\"]+)\"").matcher(json);
            if (mAlt.find()) nombre = mAlt.group(1);
        }
        
        if (json.contains("\"summary\":")) {
            Matcher m = Pattern.compile("\"summary\"\\s*:\\s*\"([^\"]+)\"").matcher(json);
            if (m.find()) sinopsis = m.group(1);
        }

        Matcher mRating = Pattern.compile("\"rating\"\\s*:\\s*([\\d.]+)").matcher(json);
        if (mRating.find()) valoracion = String.format("%.1f", Double.parseDouble(mRating.group(1)));

        Matcher mImg = Pattern.compile("\"url\"\\s*:\\s*\"(//[^\"]+)\"").matcher(json);
        if (mImg.find()) imagen = "https:" + mImg.group(1).replace("t_thumb", "t_cover_big");

        // Retornamos: Nombre, Sinopsis, Valoración, Plataformas, Imagen
        return new String[]{nombre, sinopsis, valoracion, "Elegida en sala", imagen};
    }
}