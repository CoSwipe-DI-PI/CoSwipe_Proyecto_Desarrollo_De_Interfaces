package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeliculaAPI {

    private final String API_KEY = "4f3a4064004b34bf8e0f3de58b94d0b1";

    public String[] obtenerDatos(String genero, String tipo) throws Exception {
        int idGenero = 28; 
        
        if (genero.equals("Comedia")) idGenero = 35;
        else if (genero.equals("Drama")) idGenero = 18;
        else if (genero.equals("Terror")) idGenero = 27;
        else if (genero.equals("Ciencia Ficción")) idGenero = 878;
        else if (genero.equals("Animación")) idGenero = 16;
        else if (genero.equals("Romance")) idGenero = 10749;
        else if (genero.equals("Thriller")) idGenero = 53;

        // Si el tipo es "Serie", busca en la ruta /tv, si no en /movie
        String endpoint = tipo.equals("Serie") ? "tv" : "movie";
        
        String urlString = "https://api.themoviedb.org/3/discover/" + endpoint + "?api_key=" + API_KEY 
                + "&language=es-ES&with_genres=" + idGenero 
                + "&page=" + (int)(Math.random() * 5 + 1);
        
        URL url = new URL(urlString);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("accept", "application/json");

        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        StringBuilder respuesta = new StringBuilder();

        while ((linea = lector.readLine()) != null) {
            respuesta.append(linea);
        }
        lector.close();

        String json = respuesta.toString();
        
        String titulo = "Desconocido";
        String poster = "";
        String nota = "N/A";

        // Las series usan "name" en lugar de "title"
        if (tipo.equals("Serie") && json.contains("\"name\":")) {
            String[] partes = json.split("\"name\":\"");
            titulo = partes[1].split("\"")[0];
        } else if (json.contains("\"title\":")) {
            String[] partes = json.split("\"title\":\"");
            titulo = partes[1].split("\"")[0];
        }

        if (json.contains("\"poster_path\":\"")) {
            String[] partes = json.split("\"poster_path\":\"");
            poster = partes[1].split("\"")[0];
        }

        if (json.contains("\"vote_average\":")) {
            String[] partes = json.split("\"vote_average\":");
            nota = partes[1].split(",")[0].trim();
        }
        
        if (poster == null || poster.equals("null") || poster.isEmpty()) {
            poster = "";
        } else {
            poster = "https://image.tmdb.org/t/p/w500" + poster;
        }

        return new String[]{titulo, poster, nota};
    }
}