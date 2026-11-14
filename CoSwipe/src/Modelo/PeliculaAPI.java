package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeliculaAPI {

    private final String API_KEY = "4f3a4064004b34bf8e0f3de58b94d0b1";

    public String[] obtenerDatosPelicula(String genero) throws Exception {
        int idGenero = 28; 
        
        if (genero.equals("Comedia")) idGenero = 35;
        else if (genero.equals("Drama")) idGenero = 18;
        else if (genero.equals("Terror")) idGenero = 27;
        else if (genero.equals("Ciencia Ficción")) idGenero = 878;
        else if (genero.equals("Animación")) idGenero = 16;
        else if (genero.equals("Romance")) idGenero = 10749;
        else if (genero.equals("Thriller")) idGenero = 53;

        String urlString = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY 
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

        if (json.contains("\"title\":")) {
            String[] partesTitulo = json.split("\"title\":\"");
            titulo = partesTitulo[1].split("\"")[0];
        }

        if (json.contains("\"poster_path\":\"")) {
            String[] partesPoster = json.split("\"poster_path\":\"");
            poster = partesPoster[1].split("\"")[0];
        }

        if (json.contains("\"vote_average\":")) {
            String[] partesNota = json.split("\"vote_average\":");
            nota = partesNota[1].split(",")[0].trim();
        }
        
        if (poster == null || poster.equals("null") || poster.isEmpty()) {
            poster = "";
        } else {
            // Aquí le añadimos la ruta completa para que la vista la entienda
            poster = "https://image.tmdb.org/t/p/w500" + poster;
        }

        // Devolvemos: Título, URL Imagen, Valoración
        String[] datos = {titulo, poster, nota};
        return datos;
    }
}