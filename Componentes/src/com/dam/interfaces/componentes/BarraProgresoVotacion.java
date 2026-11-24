package com.dam.interfaces.componentes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class BarraProgresoVotacion extends JPanel implements Serializable {
    public static final String PROP_COMPLETADO = "completado";
    private PropertyChangeSupport support;
    private JProgressBar progressBar;
    private boolean completado;

    public BarraProgresoVotacion() {
        support = new PropertyChangeSupport(this);
        this.completado = false;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        this.add(progressBar);
    }

    public int getProgreso() { return progressBar.getValue(); }
    public void setProgreso(int valor) {
        boolean old = this.completado;
        progressBar.setValue(valor);
        this.completado = (valor >= 100);
        if (support != null) support.firePropertyChange(PROP_COMPLETADO, old, this.completado);
    }

    public boolean isCompletado() { return completado; }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) { 
        if (support != null) support.addPropertyChangeListener(l); 
    }
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) { 
        if (support != null) support.removePropertyChangeListener(l); 
    }
}