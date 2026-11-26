package com.dam.interfaces.componentes;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndicadorParticipantesSala extends JPanel {

    private JLabel etiquetaParticipantes;
    private int numeroParticipantes;

    public IndicadorParticipantesSala() {
        this.numeroParticipantes = 0;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        etiquetaParticipantes = new JLabel("Participantes: 0");
        add(etiquetaParticipantes);
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numero) {
        if (numero >= 0) {
            this.numeroParticipantes = numero;
            etiquetaParticipantes.setText("Participantes: " + numero);
        }
    }
}