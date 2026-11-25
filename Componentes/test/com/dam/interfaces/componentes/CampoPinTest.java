package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyDescriptor;
import java.awt.Color;
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
        assertEquals(new Color(255, 204, 204), componente.getTxtPin().getBackground());
    }

    @Test
    @DisplayName("Debe notificar al listener específico cuando el PIN llega a 6 dígitos")
    void testNotificacionPinCompletado() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(CampoPin.PROP_PIN_COMPLETO, evt -> {
            notificado.set(true);
            assertEquals("", evt.getOldValue());
            assertEquals("123456", evt.getNewValue());
        });

        componente.getTxtPin().setText("123456");
        assertEquals("123456", componente.getPinCompleto());
        assertTrue(notificado.get(), "El JavaBean no notificó el PIN completo.");
    }

    @Test
    @DisplayName("El PIN solo debe estar completo con 6 dígitos")
    void testLongitudPin() {
        componente.getTxtPin().setText("12345");
        assertEquals("", componente.getPinCompleto());

        componente.getTxtPin().setText("012345");
        assertEquals("012345", componente.getPinCompleto());
        assertEquals(Color.WHITE, componente.getTxtPin().getBackground());
    }

    @Test
    @DisplayName("El campo no debe aceptar letras ni más de 6 dígitos")
    void testPinInvalido() {
        componente.getTxtPin().setText("12A456");
        assertEquals("", componente.getTxtPin().getText());

        componente.getTxtPin().setText("12-456");
        assertEquals("", componente.getTxtPin().getText());

        componente.getTxtPin().setText("1234567");
        assertEquals("", componente.getTxtPin().getText());
        assertEquals("", componente.getPinCompleto());
    }

    @Test
    @DisplayName("El PIN completo debe desaparecer al borrar el campo")
    void testBorrarPin() {
        componente.getTxtPin().setText("123456");
        assertEquals("123456", componente.getPinCompleto());

        componente.getTxtPin().setText("");
        assertEquals("", componente.getPinCompleto());
        assertEquals(new Color(255, 204, 204), componente.getTxtPin().getBackground());
    }

    @Test
    @DisplayName("Debe notificar el PIN completo a un listener general")
    void testNotificacionGeneral() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("pinCompleto", evt.getPropertyName());
            assertEquals("", evt.getOldValue());
            assertEquals("123456", evt.getNewValue());
        });

        componente.getTxtPin().setText("123456");
        assertTrue(notificado.get());
    }

    @Test
    @DisplayName("El BeanInfo debe definir pinCompleto como propiedad de solo lectura")
    void testBeanInfo() throws Exception {
        CampoPinBeanInfo info = new CampoPinBeanInfo();
        PropertyDescriptor[] propiedades = info.getPropertyDescriptors();

        assertEquals(CampoPin.class, info.getBeanDescriptor().getBeanClass());
        assertNotNull(propiedades);
        assertEquals(1, propiedades.length);
        assertEquals("pinCompleto", propiedades[0].getName());
        assertEquals("PIN Completo", propiedades[0].getDisplayName());
        assertTrue(propiedades[0].isPreferred());
        assertEquals(String.class, propiedades[0].getPropertyType());
        assertEquals("getPinCompleto", propiedades[0].getReadMethod().getName());
        assertNull(propiedades[0].getWriteMethod());
        componente.getTxtPin().setText("123456");
        assertEquals("123456", propiedades[0].getReadMethod().invoke(componente));
    }

    @Test
    @DisplayName("No debe aceptar un séptimo dígito en un PIN válido")
    void testLimitePinCompleto() {
        componente.getTxtPin().setText("123456");
        componente.getTxtPin().setCaretPosition(6);
        componente.getTxtPin().replaceSelection("7");

        assertEquals("123456", componente.getTxtPin().getText());
        assertEquals("123456", componente.getPinCompleto());
    }

    @Test
    @DisplayName("Debe notificar cuando se borra un PIN completo")
    void testNotificacionBorrado() {
        componente.getTxtPin().setText("123456");
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("pinCompleto", evt.getPropertyName());
            assertEquals("123456", evt.getOldValue());
            assertEquals("", evt.getNewValue());
        });

        componente.getTxtPin().setText("");
        assertTrue(notificado.get());
        assertEquals("", componente.getPinCompleto());
    }
}
