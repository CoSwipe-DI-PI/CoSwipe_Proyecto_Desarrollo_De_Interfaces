package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Indicador que muestra si la sala está conectada:
 * "● Conectado" en verde o "● Desconectado" en rojo.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see PanelEstadoSalaBeanInfo
 */
public class PanelEstadoSala extends JPanel {

    private JLabel etiquetaEstado;
    private boolean conectado;

     /**
     * Crea el panel en estado desconectado.
     */
    public PanelEstadoSala() {
        this.conectado = false;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        etiquetaEstado = new JLabel("● Desconectado");
        etiquetaEstado.setForeground(java.awt.Color.RED);
        add(etiquetaEstado);
    }

    /**
     * Indica si la sala está conectada.
     *
     * @return {@code true} si está conectada
     */
    public boolean isConectado() {
        return conectado;
    }

     /**
     * Cambia el estado de conexión y actualiza el texto y el color.
     *
     * @param nuevoEstado {@code true} para conectado, {@code false} para desconectado
     */
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