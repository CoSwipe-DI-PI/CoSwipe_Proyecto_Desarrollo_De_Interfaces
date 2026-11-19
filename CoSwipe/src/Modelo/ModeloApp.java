package modelo;

public class ModeloApp {
    
    private String idioma;
    private String codigoSala;
    private int participantes;
    private String categoriaElegida;
    
    private String tituloActual;
    private String detallesActuales;
    private String imagenActual;

    public ModeloApp() {
        this.idioma = "ES";
        this.codigoSala = "847 291";
        this.participantes = 1;
        this.categoriaElegida = "Peliculas";
    }

    public String getIdioma() { 
        return idioma; 
    }
    public void setIdioma(String idioma) { 
        this.idioma = idioma;
    }

    public String getCodigoSala() {
        return codigoSala; 
    }
    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala; 
    }

    public int getParticipantes() { 
        return participantes; 
    }
    public void setParticipantes(int participantes) { 
        this.participantes = participantes; 
    }

    public String getCategoriaElegida() { 
        return categoriaElegida; 
    }
    public void setCategoriaElegida(String categoriaElegida) {
        this.categoriaElegida = categoriaElegida; 
    }

    public boolean validarPin(String pinIntroducido) {
        if (pinIntroducido == null || pinIntroducido.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public void nuevoUsuarioConectado() {
        this.participantes++;
    }

    public String getTituloActual() {
        return tituloActual;
    }

    public void setTituloActual(String tituloActual) {
        this.tituloActual = tituloActual;
    }

    public String getDetallesActuales() {
        return detallesActuales;
    }

    public void setDetallesActuales(String detallesActuales) {
        this.detallesActuales = detallesActuales;
    }

    public String getImagenActual() {
        return imagenActual;
    }

    public void setImagenActual(String imagenActual) {
        this.imagenActual = imagenActual;
    }
}