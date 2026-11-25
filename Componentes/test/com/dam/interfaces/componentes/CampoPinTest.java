package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CampoPinTest {
    private CampoPin componente;

    @BeforeEach
    void setUp() {
        componente = new CampoPin();
    }

    @Test
    @DisplayName("El PIN debe estar vacío inicialmente")
    void testPinVacio() {
        assertEquals("", componente.getPinCompleto());
    }

    @Test
    @DisplayName("Debe notificar cuando el PIN llega a 6 dígitos")
    void testNotificacionPinCompletado() {
        AtomicBoolean notificado = new AtomicBoolean(false);
        
        componente.addPropertyChangeListener(CampoPin.PROP_PIN_COMPLETO, evt -> {
            notificado.set(true);
            assertEquals("123456", evt.getNewValue());
        });

        componente.getTxtPin().setText("123456"); 
        assertTrue(notificado.get(), "El JavaBean no notificó el PIN completo.");
    }
}