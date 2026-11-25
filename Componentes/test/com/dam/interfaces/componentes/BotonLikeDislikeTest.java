package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BotonLikeDislikeTest {

    private BotonLikeDislike componente;

    @BeforeEach
    void setUp() {
        componente = new BotonLikeDislike();
    }

    @Test
    @DisplayName("El estado inicial debe ser vacío")
    void testEstadoInicial() {
        assertEquals("", componente.getVoto());
    }

    @Test
    @DisplayName("El setVoto debe notificar mediante PropertyChangeEvent")
    void testNotificacionEventoPropiedad() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(BotonLikeDislike.PROP_VOTO, evt -> {
            notificado.set(true);
            assertEquals("", evt.getOldValue());
            assertEquals("LIKE", evt.getNewValue());
        });

        componente.setVoto("LIKE");
        assertTrue(notificado.get(), "El JavaBean no notificó el cambio.");
        assertEquals("LIKE", componente.getVoto());
    }
}