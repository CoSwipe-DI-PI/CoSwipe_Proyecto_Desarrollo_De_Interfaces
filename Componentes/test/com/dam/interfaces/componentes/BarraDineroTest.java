package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.*;

class BarraDineroTest {

    private BarraDinero componente;
    private List<PropertyChangeEvent> eventos;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente = new BarraDinero());
        eventos = new ArrayList<>();
        componente.addPropertyChangeListener(eventos::add);
    }

    @Test
    @DisplayName("El rango inicial debe ser 20 € - 80 €")
    void testValoresIniciales() {
        assertAll(
            () -> assertEquals(20, componente.getMinimo()),
            () -> assertEquals(80, componente.getMaximo())
        );
    }

    @Test
    @DisplayName("Cambiar el mínimo debe disparar PROP_MINIMO con valor antiguo y nuevo")
    void testEventoMinimo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMinimo(30));

        assertEquals(1, eventos.size());
        PropertyChangeEvent evt = eventos.get(0);
        assertEquals(BarraDinero.PROP_MINIMO, evt.getPropertyName());
        assertEquals(20, evt.getOldValue());
        assertEquals(30, evt.getNewValue());
    }

    @Test
    @DisplayName("Cambiar el máximo debe disparar PROP_MAXIMO con valor antiguo y nuevo")
    void testEventoMaximo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMaximo(60));

        assertEquals(1, eventos.size());
        PropertyChangeEvent evt = eventos.get(0);
        assertEquals(BarraDinero.PROP_MAXIMO, evt.getPropertyName());
        assertEquals(80, evt.getOldValue());
        assertEquals(60, evt.getNewValue());
    }

    @Test
    @DisplayName("El mínimo no puede superar al máximo")
    void testMinimoNoSuperaMaximo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMinimo(90));

        assertEquals(80, componente.getMinimo());
    }

    @Test
    @DisplayName("El máximo no puede bajar del mínimo")
    void testMaximoNoBajaDelMinimo() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMaximo(0));

        assertEquals(20, componente.getMaximo());
    }

    @Test
    @DisplayName("El máximo no puede superar 100")
    void testMaximoNoSuperaCien() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMaximo(150));

        assertEquals(BarraDinero.VALOR_MAX, componente.getMaximo());
    }

    @Test
    @DisplayName("No debe disparar evento si el valor no cambia")
    void testSinEventoSiNoCambia() throws Exception {
        SwingUtilities.invokeAndWait(() -> componente.setMinimo(20));

        assertTrue(eventos.isEmpty(), "No debía emitirse ningún evento.");
    }
}