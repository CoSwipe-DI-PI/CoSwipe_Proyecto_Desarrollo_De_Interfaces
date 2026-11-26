package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("El voto inicial debe estar vacío")
    void testVotoInicial() {
        assertEquals("", componente.getVoto());
    }

    @Test
    @DisplayName("Debe guardar el voto LIKE correctamente")
    void testGuardarLike() {
        componente.setVoto("LIKE");
        assertEquals("LIKE", componente.getVoto());
    }

    @Test
    @DisplayName("Debe guardar el voto DISLIKE correctamente")
    void testGuardarDislike() {
        componente.setVoto("DISLIKE");
        assertEquals("DISLIKE", componente.getVoto());
    }
}