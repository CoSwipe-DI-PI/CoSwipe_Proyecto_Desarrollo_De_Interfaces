package com.dam.interfaces.componentes;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * Etiqueta que muestra cuántos participantes hay en la sala de CoSwipe.
 * <p>
 * Cada cambio del número se notifica con la propiedad
 * {@code numeroParticipantes} para que la vista o el controlador reaccionen.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see IndicadorParticipantesSalaBeanInfo
 */
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
    /** {@inheritDoc} */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        cambios.addPropertyChangeListener(listener);
    }
    /** {@inheritDoc} */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        cambios.removePropertyChangeListener(listener);
    }
}
