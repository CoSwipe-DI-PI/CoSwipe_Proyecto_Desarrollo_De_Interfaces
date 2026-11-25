package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyDescriptor;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
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
    @DisplayName("El setVoto debe notificar al listener específico de voto")
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

    @Test
    @DisplayName("Los botones deben cambiar el voto a LIKE y DISLIKE")
    void testBotones() {
        assertEquals(2, componente.getComponentCount());
        JButton dislike = (JButton) componente.getComponent(0);
        JButton like = (JButton) componente.getComponent(1);

        like.doClick(0);
        assertEquals("LIKE", componente.getVoto());
        dislike.doClick(0);
        assertEquals("DISLIKE", componente.getVoto());
    }

    @Test
    @DisplayName("Debe notificar el cambio de voto a un listener general")
    void testNotificacionGeneral() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("voto", evt.getPropertyName());
            assertEquals("", evt.getOldValue());
            assertEquals("DISLIKE", evt.getNewValue());
        });

        componente.setVoto("DISLIKE");
        assertTrue(notificado.get());
    }

    @Test
    @DisplayName("El BeanInfo debe definir correctamente la propiedad voto")
    void testBeanInfo() throws Exception {
        BotonLikeDislikeBeanInfo info = new BotonLikeDislikeBeanInfo();
        PropertyDescriptor[] propiedades = info.getPropertyDescriptors();

        assertEquals(BotonLikeDislike.class, info.getBeanDescriptor().getBeanClass());
        assertNotNull(propiedades);
        assertEquals(1, propiedades.length);
        assertEquals("voto", propiedades[0].getName());
        assertEquals("Voto Actual", propiedades[0].getDisplayName());
        assertTrue(propiedades[0].isPreferred());
        assertEquals(String.class, propiedades[0].getPropertyType());
        assertEquals("getVoto", propiedades[0].getReadMethod().getName());
        assertEquals("setVoto", propiedades[0].getWriteMethod().getName());
        propiedades[0].getWriteMethod().invoke(componente, "LIKE");
        assertEquals("LIKE", propiedades[0].getReadMethod().invoke(componente));
    }

    @Test
    @DisplayName("No debe notificar un voto repetido ni avisar a un listener eliminado")
    void testVotoRepetidoYEliminarListener() {
        AtomicInteger notificaciones = new AtomicInteger(0);
        PropertyChangeListener listener = evt -> notificaciones.incrementAndGet();
        componente.addPropertyChangeListener(listener);

        componente.setVoto("LIKE");
        componente.setVoto("LIKE");
        assertEquals(1, notificaciones.get());

        componente.removePropertyChangeListener(listener);
        componente.setVoto("DISLIKE");
        assertEquals(1, notificaciones.get());
        assertEquals("DISLIKE", componente.getVoto());
    }
}
