package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Etiqueta que muestra cuántos participantes hay en la sala de CoSwipe.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see IndicadorParticipantesSalaBeanInfo
 */
public class IndicadorParticipantesSala extends JPanel {

    private JLabel etiquetaParticipantes;
    private int numeroParticipantes;

    /**
     * Crea el indicador con 0 participantes.
     */
    public IndicadorParticipantesSala() {
        this.numeroParticipantes = 0;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        etiquetaParticipantes = new JLabel("Participantes: 0");
        add(etiquetaParticipantes);
    }

     /**
     * Devuelve el número de participantes actual.
     *
     * @return número de participantes en la sala
     */
    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    /**
     * Cambia el número de participantes y actualiza el texto.
     * Los valores negativos se ignoran.
     *
     * @param numero nuevo número de participantes (0 o más)
     */
    public void setNumeroParticipantes(int numero) {
        if (numero >= 0) {
            this.numeroParticipantes = numero;
            etiquetaParticipantes.setText("Participantes: " + numero);
        }
    }
}