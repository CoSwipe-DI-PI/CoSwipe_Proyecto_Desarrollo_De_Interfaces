package com.dam.interfaces.componentes;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IndicadorParticipantesSala extends JPanel {

    private int numeroParticipantes;
    private JLabel lblParticipantes;
    private PropertyChangeSupport cambios;

    public IndicadorParticipantesSala() {
        cambios = new PropertyChangeSupport(this);
        numeroParticipantes = 0;

        setLayout(new BorderLayout());

        lblParticipantes = new JLabel("Participantes: 0");
        lblParticipantes.setHorizontalAlignment(SwingConstants.CENTER);

        add(lblParticipantes, BorderLayout.CENTER);
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        if (numeroParticipantes < 0) {
            throw new IllegalArgumentException(
                    "El número de participantes no puede ser negativo"
            );
        }

        int anterior = this.numeroParticipantes;
        this.numeroParticipantes = numeroParticipantes;

        lblParticipantes.setText("Participantes: " + numeroParticipantes);

        cambios.firePropertyChange(
                "numeroParticipantes",
                anterior,
                numeroParticipantes
        );
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        cambios.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        cambios.removePropertyChangeListener(listener);
    }
}
