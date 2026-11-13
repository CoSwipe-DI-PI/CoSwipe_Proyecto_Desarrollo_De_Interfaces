package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeliculaAPI {

    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjNhNDA2NDA0YjRmOGUwZjNkZTU4Yjk0ZDBiMSIsIm5iZiI6MTcyODQ2MzE2NiwiZXhwIjoxNzMxMDU1MTY2LCJzdWIiOiI2NzA2MjcyYzU0NzBlMDBkZGE1NGU2YzciLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.iB3BQcxYX_9WfJ0n14F3w-VNfF3x4Wq_1D5Jv2oN1X8";

    public String[] obtenerDatosPelicula(String genero) throws Exception {
        int idGenero = 28;
        
        if (genero.equals("Comedia")) idGenero = 35;
        else if (genero.equals("Drama")) idGenero = 18;
        else if (genero.equals("Terror")) idGenero = 27;
        else if (genero.equals("Ciencia Ficción")) idGenero = 878;

        String urlString = "https://api.themoviedb.org/3/discover/movie?language=es-ES&with_genres=" + idGenero;
        URL url = new URL(urlString);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Authorization", "Bearer " + TOKEN);
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

        if (json.contains("\"title\":")) {
            String[] partesTitulo = json.split("\"title\":\"");
            String[] resto = partesTitulo[1].split("\"");
            titulo = resto[0];
        }

        if (json.contains("\"poster_path\":\"")) {
            String[] partesPoster = json.split("\"poster_path\":\"");
            String[] resto = partesPoster[1].split("\"");
            poster = resto[0];
        }

        String[] datos = {titulo, poster};
        return datos;
    }
}