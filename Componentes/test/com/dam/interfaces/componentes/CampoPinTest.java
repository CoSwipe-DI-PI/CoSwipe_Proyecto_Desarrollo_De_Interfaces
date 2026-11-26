package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Color;

public class CampoPinTest {
    
    private CampoPin componente;

    @BeforeEach
    void setUp() {
        componente = new CampoPin();
    }

    @Test
    @DisplayName("Al arrancar, el PIN debe estar vacío y de color blanco")
    void testPinVacio() {
        assertEquals("", componente.getPinCompleto());
        assertEquals(Color.WHITE, componente.getCampoTexto().getBackground());
    }

    @Test
    @DisplayName("Si el PIN tiene 6 dígitos, getPinCompleto los devuelve")
    void testPinCompleto() {
        componente.getCampoTexto().setText("123456");
        assertEquals("123456", componente.getPinCompleto());
    }

    @Test
    @DisplayName("Si el PIN tiene menos de 6 dígitos, getPinCompleto está vacío")
    void testPinCortoDevuelveVacio() {
        componente.getCampoTexto().setText("123");
        assertEquals("", componente.getPinCompleto());
    }
}