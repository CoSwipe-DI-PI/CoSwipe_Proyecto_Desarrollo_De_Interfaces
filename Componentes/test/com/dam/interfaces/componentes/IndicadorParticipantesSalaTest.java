package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IndicadorParticipantesSalaTest {
    
    private IndicadorParticipantesSala componente;

    @BeforeEach
    void setUp() {
        componente = new IndicadorParticipantesSala();
    }

    @Test
    @DisplayName("El estado inicial debe ser 0")
    void testEstadoInicial() {
        assertEquals(0, componente.getNumeroParticipantes());
    }

    @Test
    @DisplayName("Debe actualizar el número de participantes")
    void testActualizarParticipantes() {
        componente.setNumeroParticipantes(5);
        assertEquals(5, componente.getNumeroParticipantes());
    }

    @Test
    @DisplayName("No debe permitir números negativos")
    void testNumeroNegativo() {
        // Le metemos 3 primero para que no sea 0
        componente.setNumeroParticipantes(3);
        
        // Intentamos poner -2 (debería ignorarlo y quedarse en 3)
        componente.setNumeroParticipantes(-2);
        
        assertEquals(3, componente.getNumeroParticipantes());
    }
}