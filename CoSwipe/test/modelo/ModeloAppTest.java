
package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la clase ModeloApp.
 *
 * @author Raúl Molina Cordones
 */
public class ModeloAppTest {

    // Comprobar los valores iniciales del modelo.
    @Test
    public void comprobarValoresIniciales() {

        ModeloApp modelo = new ModeloApp();

        assertEquals("ES", modelo.getIdioma());
        assertEquals("847 291", modelo.getCodigoSala());
        assertEquals(1, modelo.getParticipantes());
        assertEquals("Peliculas", modelo.getCategoriaElegida());
    }

    // Comprobar que se puede modificar el idioma.
    @Test
    public void comprobarCambioIdioma() {

        ModeloApp modelo = new ModeloApp();

        modelo.setIdioma("EN");

        assertEquals("EN", modelo.getIdioma());
    }

    // Comprobar que aumenta el número de participantes.
    @Test
    public void comprobarNuevoParticipante() {

        ModeloApp modelo = new ModeloApp();

        modelo.nuevoUsuarioConectado();

        assertEquals(2, modelo.getParticipantes());
    }

    // Comprobar que no se aceptan PIN vacíos o nulos.
    @Test
    public void comprobarPinVacio() {

        ModeloApp modelo = new ModeloApp();

        assertFalse(modelo.validarPin(null));
        assertFalse(modelo.validarPin(""));
        assertFalse(modelo.validarPin("   "));
    }

    // Comprobar que se acepta un PIN con contenido.
    @Test
    public void comprobarPinValido() {

        ModeloApp modelo = new ModeloApp();

        assertTrue(modelo.validarPin("847291"));
    }

    // Comprobar que se pueden modificar los participantes.
    @Test
    public void comprobarCambioParticipantes() {

        ModeloApp modelo = new ModeloApp();

        modelo.setParticipantes(5);

        assertEquals(5, modelo.getParticipantes());
    }

    // Comprobar que se puede modificar la categoría.
    @Test
    public void comprobarCambioCategoria() {

        ModeloApp modelo = new ModeloApp();

        modelo.setCategoriaElegida("Videojuegos");

        assertEquals("Videojuegos", modelo.getCategoriaElegida());
    }
}
