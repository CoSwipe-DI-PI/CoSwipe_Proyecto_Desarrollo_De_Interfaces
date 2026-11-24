package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BarraProgresoVotacionTest {
    private BarraProgresoVotacion componente;

    @BeforeEach
    void setUp() {
        componente = new BarraProgresoVotacion();
    }

    @Test
    @DisplayName("Debe empezar en 0 y no completado")
    void testEstadoInicial() {
        assertEquals(0, componente.getProgreso());
        assertFalse(componente.isCompletado());
    }

    @Test
    @DisplayName("Debe notificar al llegar al 100%")
    void testNotificacionCompletado() {
        AtomicBoolean notificado = new AtomicBoolean(false);
        
        componente.addPropertyChangeListener(BarraProgresoVotacion.PROP_COMPLETADO, evt -> {
            notificado.set(true);
            assertEquals(true, evt.getNewValue());
        });

        componente.setProgreso(100);
        assertTrue(notificado.get(), "No se notificó la finalización.");
        assertTrue(componente.isCompletado());
    }
}