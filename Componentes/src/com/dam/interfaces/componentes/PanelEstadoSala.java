package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Indicador visual del estado de conexión a la sala de CoSwipe.
 * <p>
 * Muestra "🟢 Conectado" en verde o " 🔴 Desconectado" en rojo y notifica
 * cada cambio mediante la propiedad {@link #PROP_CONECTADO}.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see PanelEstadoSalaBeanInfo
 */
public class PanelEstadoSala extends JPanel implements Serializable {

    public static final String PROP_CONECTADO = "conectado";
    private PropertyChangeSupport support;
    private JLabel lblEstado;
    private boolean conectado;

    /**
     * Crea el panel de la sala en estado desconectado 
     */
    public PanelEstadoSala() {
        support = new PropertyChangeSupport(this);
        this.conectado = false;
        inicializarInterfaz();
    }
    /**
     * Crea la etiqueta de estado con el texto y el color de "Desconectado".
     */
    private void inicializarInterfaz() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);
        lblEstado = new JLabel("● Desconectado");
        lblEstado.setForeground(java.awt.Color.RED);
        add(lblEstado);
    }
    /**
     * Indica si la sala está conectada.
     *
     * @return {@code true} si está conectado, {@code false} si no
     */
    public boolean isConectado() {
        return conectado;
    }
    /**
     * Cambia el estado de conexión, actualiza texto y color
     * y notifica el cambio a los oyentes.
     *
     * @param nuevoEstado {@code true} para conectado, {@code false} para desconectado
     */
    public void setConectado(boolean nuevoEstado) {
        boolean old = this.conectado;
        this.conectado = nuevoEstado;

        if (nuevoEstado) {
            lblEstado.setText("● Conectado");
            lblEstado.setForeground(new java.awt.Color(34, 197, 94));
        } else {
            lblEstado.setText("● Desconectado");
            lblEstado.setForeground(java.awt.Color.RED);
        }
        if (support != null) {
            support.firePropertyChange(PROP_CONECTADO, old, nuevoEstado);
        }
    }
    /** {@inheritDoc} */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.addPropertyChangeListener(l);
        }
    }
    /** {@inheritDoc} */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.removePropertyChangeListener(l);
        }
    }
}
