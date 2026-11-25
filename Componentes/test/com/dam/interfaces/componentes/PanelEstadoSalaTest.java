package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PanelEstadoSalaTest {
    private PanelEstadoSala componente;

    @BeforeEach
    void setUp() {
        componente = new PanelEstadoSala();
    }

    @Test
    @DisplayName("Debe iniciar desconectado")
    void testEstadoInicial() {
        assertFalse(componente.isConectado());
    }

    @Test
    @DisplayName("Debe notificar al conectarse")
    void testNotificacionConexion() {
        AtomicBoolean notificado = new AtomicBoolean(false);
        
        componente.addPropertyChangeListener(PanelEstadoSala.PROP_CONECTADO, evt -> {
            notificado.set(true);
            assertEquals(true, evt.getNewValue());
        });

        componente.setConectado(true);
        assertTrue(notificado.get(), "No se notificó la conexión.");
    }
}