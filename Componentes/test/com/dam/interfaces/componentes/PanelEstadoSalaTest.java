package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyDescriptor;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Color;
import javax.swing.JLabel;
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
        JLabel etiqueta = (JLabel) componente.getComponent(0);
        assertEquals("● Desconectado", etiqueta.getText());
        assertEquals(Color.RED, etiqueta.getForeground());
    }

    @Test
    @DisplayName("Debe notificar al listener específico al conectarse")
    void testNotificacionConexion() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(PanelEstadoSala.PROP_CONECTADO, evt -> {
            notificado.set(true);
            assertEquals(false, evt.getOldValue());
            assertEquals(true, evt.getNewValue());
        });

        componente.setConectado(true);
        assertTrue(componente.isConectado());
        assertTrue(notificado.get(), "No se notificó la conexión.");
    }

    @Test
    @DisplayName("Debe actualizar el estado, texto y color de conexión")
    void testEstadoConexion() {
        JLabel etiqueta = (JLabel) componente.getComponent(0);
        componente.setConectado(true);
        assertTrue(componente.isConectado());
        assertEquals("● Conectado", etiqueta.getText());
        assertEquals(new Color(34, 197, 94), etiqueta.getForeground());

        componente.setConectado(false);
        assertFalse(componente.isConectado());
        assertEquals("● Desconectado", etiqueta.getText());
        assertEquals(Color.RED, etiqueta.getForeground());
    }

    @Test
    @DisplayName("Debe notificar la conexión a un listener general")
    void testNotificacionGeneral() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("conectado", evt.getPropertyName());
            assertEquals(false, evt.getOldValue());
            assertEquals(true, evt.getNewValue());
        });

        componente.setConectado(true);
        assertTrue(notificado.get());
    }

    @Test
    @DisplayName("El BeanInfo debe definir correctamente la propiedad conectado")
    void testBeanInfo() throws Exception {
        PanelEstadoSalaBeanInfo info = new PanelEstadoSalaBeanInfo();
        PropertyDescriptor[] propiedades = info.getPropertyDescriptors();

        assertEquals(PanelEstadoSala.class, info.getBeanDescriptor().getBeanClass());
        assertNotNull(propiedades);
        assertEquals(1, propiedades.length);
        assertEquals("conectado", propiedades[0].getName());
        assertEquals("Conectado", propiedades[0].getDisplayName());
        assertTrue(propiedades[0].isPreferred());
        assertEquals(boolean.class, propiedades[0].getPropertyType());
        assertEquals("isConectado", propiedades[0].getReadMethod().getName());
        assertEquals("setConectado", propiedades[0].getWriteMethod().getName());
        propiedades[0].getWriteMethod().invoke(componente, true);
        assertEquals(true, propiedades[0].getReadMethod().invoke(componente));
    }

    @Test
    @DisplayName("Debe notificar al desconectarse")
    void testNotificacionDesconexion() {
        componente.setConectado(true);
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("conectado", evt.getPropertyName());
            assertEquals(true, evt.getOldValue());
            assertEquals(false, evt.getNewValue());
        });

        componente.setConectado(false);
        assertTrue(notificado.get());
        assertFalse(componente.isConectado());
    }

    @Test
    @DisplayName("No debe notificar un estado repetido ni avisar a un listener eliminado")
    void testEstadoRepetidoYEliminarListener() {
        AtomicInteger notificaciones = new AtomicInteger(0);
        PropertyChangeListener listener = evt -> notificaciones.incrementAndGet();
        componente.addPropertyChangeListener(listener);

        componente.setConectado(true);
        componente.setConectado(true);
        assertEquals(1, notificaciones.get());

        componente.removePropertyChangeListener(listener);
        componente.setConectado(false);
        assertEquals(1, notificaciones.get());
        assertFalse(componente.isConectado());
    }
}
