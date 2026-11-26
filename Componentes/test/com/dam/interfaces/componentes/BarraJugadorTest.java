package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.*;

class BarraJugadorTest {

    private BarraJugador componente;
    private List<PropertyChangeEvent> eventos;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente = new BarraJugador());
        eventos = new ArrayList<>();
        componente.addPropertyChangeListener(eventos::add);
    }

    @Test
    @DisplayName("El número inicial de jugadores debe ser 2")
    void testValorInicial() {
        assertEquals(2, componente.getJugadores());
    }

    @Test
    @DisplayName("Cambiar los jugadores debe disparar PROP_JUGADORES con valor antiguo y nuevo")
    void testEventoJugadores() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setJugadores(5));

        assertEquals(1, eventos.size());
        PropertyChangeEvent evt = eventos.get(0);
        assertEquals(BarraJugador.PROP_JUGADORES, evt.getPropertyName());
        assertEquals(2, evt.getOldValue());
        assertEquals(5, evt.getNewValue());
    }

    @Test
    @DisplayName("El número de jugadores no puede superar el máximo")
    void testNoSuperaMaximo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setJugadores(15));

        assertEquals(BarraJugador.MAX_JUGADORES, componente.getJugadores());
    }

    @Test
    @DisplayName("El número de jugadores no puede bajar del mínimo")
    void testNoBajaDelMinimo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setJugadores(0));

        assertEquals(BarraJugador.MIN_JUGADORES, componente.getJugadores());
    }

    @Test
    @DisplayName("No debe disparar evento si el valor no cambia")
    void testSinEventoSiNoCambia() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setJugadores(2));

        assertTrue(eventos.isEmpty(), "No debía emitirse ningún evento.");
    }
}