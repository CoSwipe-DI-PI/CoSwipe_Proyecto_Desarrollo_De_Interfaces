package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelectorGeneroTest {
    private SelectorGenero componente;

    @BeforeEach
    void setUp() {
        componente = new SelectorGenero();
    }

    @Test
    @DisplayName("El género por defecto debe ser Acción")
    void testGeneroPorDefecto() {
        assertEquals("Acción", componente.getGenero());
    }

    @Test
    @DisplayName("El setGenero debe notificar el cambio")
    void testNotificacionGenero() {
        AtomicBoolean notificado = new AtomicBoolean(false);
        
        componente.addPropertyChangeListener(SelectorGenero.PROP_GENERO, evt -> {
            notificado.set(true);
            assertEquals("Comedia", evt.getNewValue());
        });

        componente.setGenero("Comedia");
        assertTrue(notificado.get(), "No se notificó el cambio de género.");
    }
}