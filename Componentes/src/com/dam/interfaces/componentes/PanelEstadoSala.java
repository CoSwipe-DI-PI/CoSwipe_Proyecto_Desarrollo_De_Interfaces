package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEstadoSala extends JPanel {

    private JLabel etiquetaEstado;
    private boolean conectado;

    public PanelEstadoSala() {
        this.conectado = false;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        etiquetaEstado = new JLabel("● Desconectado");
        etiquetaEstado.setForeground(java.awt.Color.RED);
        add(etiquetaEstado);
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean nuevoEstado) {
        this.conectado = nuevoEstado;
        
        if (nuevoEstado) {
            etiquetaEstado.setText("● Conectado");
            etiquetaEstado.setForeground(new java.awt.Color(34, 197, 94)); // Verde
        } else {
            etiquetaEstado.setText("● Desconectado");
            etiquetaEstado.setForeground(java.awt.Color.RED);
        }
    }
}