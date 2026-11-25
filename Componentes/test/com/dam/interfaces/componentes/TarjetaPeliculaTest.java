package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TarjetaPeliculaTest {
    private TarjetaPelicula componente;

    @BeforeEach
    void setUp() {
        componente = new TarjetaPelicula();
    }

    @Test
    @DisplayName("El estado inicial debe tener valores por defecto")
    void testEstadoInicial() {
        assertEquals("Sin título", componente.getTitulo());
        assertEquals("Sin descripción", componente.getSinopsis());
    }

    @Test
    @DisplayName("Cambiar título debe notificar evento")
    void testNotificacionTitulo() {
        AtomicBoolean notificado = new AtomicBoolean(false);
        
        componente.addPropertyChangeListener(TarjetaPelicula.PROP_TITULO, evt -> {
            notificado.set(true);
            assertEquals("Dune", evt.getNewValue());
        });

        componente.setTitulo("Dune");
        assertTrue(notificado.get(), "No se notificó el cambio de título.");
    }
}