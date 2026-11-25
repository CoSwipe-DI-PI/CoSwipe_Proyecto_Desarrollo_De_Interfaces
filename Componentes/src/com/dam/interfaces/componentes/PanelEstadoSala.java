package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEstadoSala extends JPanel implements Serializable {

    public static final String PROP_CONECTADO = "conectado";
    private PropertyChangeSupport support;
    private JLabel lblEstado;
    private boolean conectado;

    public PanelEstadoSala() {
        support = new PropertyChangeSupport(this);
        this.conectado = false;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);
        lblEstado = new JLabel("● Desconectado");
        lblEstado.setForeground(java.awt.Color.RED);
        add(lblEstado);
    }

    public boolean isConectado() {
        return conectado;
    }

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

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.addPropertyChangeListener(l);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.removePropertyChangeListener(l);
        }
    }
}
