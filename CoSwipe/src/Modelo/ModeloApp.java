package modelo;

/**
 * Modelo de datos de la aplicación CoSwipe.
 *
 * <p>Almacena el estado compartido de la sala y ofrece acceso a él
 * mediante métodos getter y setter. Los datos que guarda son:</p>
 * <ul>
 *   <li>El idioma de la interfaz.</li>
 *   <li>El código (PIN) de la sala.</li>
 *   <li>El número de participantes conectados.</li>
 *   <li>La categoría elegida para la sesión.</li>
 * </ul>
 *
 * <p>También incluye la validación del PIN de sala y el registro de
 * nuevos usuarios conectados. El controlador {@code ControlApp}
 * consulta y modifica este modelo.</p>
 *
 * @author Sergio Garcia
 * @version 1.0
 * @see control.ControlApp
 */
public class ModeloApp {

    private String idioma;
    private String codigoSala;
    private int participantes;
    private String categoriaElegida;

    /**
     * Crea el modelo con los valores iniciales por defecto:
     * idioma {@code "ES"}, código de sala {@code "847 291"},
     * un participante y categoría {@code "Peliculas"}.
     */
    public ModeloApp() {
        this.idioma = "ES";
        this.codigoSala = "847 291";
        this.participantes = 1;
        this.categoriaElegida = "Peliculas";
    }

    /**
     * Devuelve el idioma actual de la interfaz.
     *
     * @return código del idioma, por ejemplo {@code "ES"} o {@code "EN"}
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma de la interfaz.
     *
     * @param idioma código del idioma, por ejemplo {@code "ES"} o {@code "EN"}
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Devuelve el código (PIN) de la sala actual.
     *
     * @return código de la sala, por ejemplo {@code "847 291"}
     */
    public String getCodigoSala() {
        return codigoSala;
    }

    /**
     * Establece el código (PIN) de la sala.
     *
     * @param codigoSala nuevo código de la sala
     */
    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    /**
     * Devuelve el número de participantes que hay en la sala.
     *
     * @return número de participantes conectados
     */
    public int getParticipantes() {
        return participantes;
    }

    /**
     * Establece el número de participantes de la sala.
     *
     * @param participantes número total de participantes conectados
     */
    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    /**
     * Devuelve la categoría elegida para la sesión.
     *
     * @return categoría elegida, por ejemplo {@code "Peliculas"}
     */
    public String getCategoriaElegida() {
        return categoriaElegida;
    }

    /**
     * Establece la categoría elegida para la sesión.
     *
     * @param categoriaElegida nombre de la categoría, por ejemplo
     *                         {@code "Peliculas"}
     */
    public void setCategoriaElegida(String categoriaElegida) {
        this.categoriaElegida = categoriaElegida;
    }

    /**
     * Comprueba si el PIN introducido por el usuario es válido.
     *
     * <p>De momento solo verifica que el PIN no sea {@code null} ni esté
     * vacío o formado únicamente por espacios. No lo compara con el código
     * real de la sala.</p>
     *
     * @param pinIntroducido PIN escrito por el usuario; puede ser {@code null}
     * @return {@code true} si el PIN tiene contenido,
     *         {@code false} si es {@code null} o está vacío
     */
    public boolean validarPin(String pinIntroducido) {
        if (pinIntroducido == null || pinIntroducido.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Registra la conexión de un nuevo usuario a la sala,
     * incrementando en uno el número de participantes.
     */
    public void nuevoUsuarioConectado() {
        this.participantes++;
    }
}
