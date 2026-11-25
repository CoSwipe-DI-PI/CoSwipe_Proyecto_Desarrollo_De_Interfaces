package com.dam.interfaces.componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyDescriptor;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JLabel;
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
    @DisplayName("El número de participantes debe ser 0 inicialmente")
    void testEstadoInicial() {
        assertEquals(0, componente.getNumeroParticipantes());
        JLabel etiqueta = (JLabel) componente.getComponent(0);
        assertEquals("Participantes: 0", etiqueta.getText());
    }

    @Test
    @DisplayName("Debe actualizar el número de participantes y su etiqueta")
    void testActualizarParticipantes() {
        JLabel etiqueta = (JLabel) componente.getComponent(0);
        componente.setNumeroParticipantes(5);
        assertEquals(5, componente.getNumeroParticipantes());
        assertEquals("Participantes: 5", etiqueta.getText());

        componente.setNumeroParticipantes(2);
        assertEquals(2, componente.getNumeroParticipantes());
        assertEquals("Participantes: 2", etiqueta.getText());

        componente.setNumeroParticipantes(0);
        assertEquals(0, componente.getNumeroParticipantes());
        assertEquals("Participantes: 0", etiqueta.getText());
    }

    @Test
    @DisplayName("El número de participantes no puede ser negativo")
    void testParticipantesNegativos() {
        componente.setNumeroParticipantes(3);
        assertThrows(IllegalArgumentException.class, () -> {
            componente.setNumeroParticipantes(-1);
        });
        assertEquals(3, componente.getNumeroParticipantes());
        JLabel etiqueta = (JLabel) componente.getComponent(0);
        assertEquals("Participantes: 3", etiqueta.getText());
    }

    @Test
    @DisplayName("Debe notificar al listener específico cuando cambian los participantes")
    void testNotificacionParticipantes() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener("numeroParticipantes", evt -> {
            notificado.set(true);
            assertEquals(0, evt.getOldValue());
            assertEquals(5, evt.getNewValue());
        });

        componente.setNumeroParticipantes(5);
        assertTrue(notificado.get(), "No se notificó el cambio de participantes.");
    }

    @Test
    @DisplayName("Debe notificar los participantes a un listener general")
    void testNotificacionGeneral() {
        AtomicBoolean notificado = new AtomicBoolean(false);

        componente.addPropertyChangeListener(evt -> {
            notificado.set(true);
            assertEquals("numeroParticipantes", evt.getPropertyName());
            assertEquals(0, evt.getOldValue());
            assertEquals(5, evt.getNewValue());
        });

        componente.setNumeroParticipantes(5);
        assertTrue(notificado.get());
    }

    @Test
    @DisplayName("El BeanInfo debe definir la propiedad numeroParticipantes")
    void testBeanInfo() throws Exception {
        IndicadorParticipantesSalaBeanInfo info = new IndicadorParticipantesSalaBeanInfo();
        PropertyDescriptor[] propiedades = info.getPropertyDescriptors();

        assertNotNull(propiedades);
        assertEquals(1, propiedades.length);
        assertEquals("numeroParticipantes", propiedades[0].getName());
        assertEquals("Número de participantes", propiedades[0].getDisplayName());
        assertEquals(int.class, propiedades[0].getPropertyType());
        assertEquals("getNumeroParticipantes", propiedades[0].getReadMethod().getName());
        assertEquals("setNumeroParticipantes", propiedades[0].getWriteMethod().getName());
        propiedades[0].getWriteMethod().invoke(componente, 5);
        assertEquals(5, propiedades[0].getReadMethod().invoke(componente));
    }

    @Test
    @DisplayName("No debe notificar el mismo número ni avisar a un listener eliminado")
    void testValorRepetidoYEliminarListener() {
        AtomicInteger notificaciones = new AtomicInteger(0);
        PropertyChangeListener listener = evt -> notificaciones.incrementAndGet();
        componente.addPropertyChangeListener(listener);

        componente.setNumeroParticipantes(3);
        componente.setNumeroParticipantes(3);
        assertEquals(1, notificaciones.get());

        componente.removePropertyChangeListener(listener);
        componente.setNumeroParticipantes(0);
        assertEquals(1, notificaciones.get());
        assertEquals(0, componente.getNumeroParticipantes());
    }
}
