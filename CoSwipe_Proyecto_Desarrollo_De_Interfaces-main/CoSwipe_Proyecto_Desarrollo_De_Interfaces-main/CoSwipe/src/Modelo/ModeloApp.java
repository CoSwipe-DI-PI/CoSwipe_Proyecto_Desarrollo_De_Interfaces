package modelo;

public class ModeloApp {
    private String idioma;
    private String[] idiomasDisponibles;

    public ModeloApp() {
        this.idioma = "EN"; // Inglés por defecto
        this.idiomasDisponibles = new String[]{"EN", "ES"};
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String[] getIdiomasDisponibles() {
        return idiomasDisponibles;
    }

    public String traducir(String clave) {
        if (idioma.equals("ES")) {
            switch (clave) {
                case "btn_crear": return "Crear sala";
                case "btn_unirse": return "Unirse a sala";
                case "txt_codigo": return "CÓDIGO DE SALA";
                case "btn_empezar": return "Empezar a deslizar";
                default: return clave;
            }
        } else {
            switch (clave) {
                case "btn_crear": return "Create room";
                case "btn_unirse": return "Join room";
                case "txt_codigo": return "ROOM CODE";
                case "btn_empezar": return "Start swiping";
                default: return clave;
            }
        }
    }
}