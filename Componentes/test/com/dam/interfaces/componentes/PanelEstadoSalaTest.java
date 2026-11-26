package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("Debe cambiar a conectado")
    void testCambiarAConectado() {
        componente.setConectado(true);
        assertTrue(componente.isConectado());
    }

    @Test
    @DisplayName("Debe volver a desconectado")
    void testVolverADesconectado() {
        componente.setConectado(true);
        componente.setConectado(false);
        assertFalse(componente.isConectado());
    }
}