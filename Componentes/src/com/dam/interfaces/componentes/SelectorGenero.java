package com.dam.interfaces.componentes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SelectorGenero extends JPanel implements Serializable {
    public static final String PROP_GENERO = "genero";
    private PropertyChangeSupport support;
    private JComboBox<String> cmbGeneros;
    private String genero;

    public SelectorGenero() {
        support = new PropertyChangeSupport(this);
        this.genero = "Acción";
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        String[] generos = {"Acción", "Comedia", "Drama", "Terror", "Ciencia Ficción"};
        cmbGeneros = new JComboBox<>(generos);
        this.add(cmbGeneros);

        cmbGeneros.addActionListener(e -> {
            String old = this.genero;
            this.genero = (String) cmbGeneros.getSelectedItem();
            if (support != null) support.firePropertyChange(PROP_GENERO, old, this.genero);
        });
    }

    public String getGenero() { return genero; }
    public void setGenero(String nuevoGenero) {
        String old = this.genero;
        this.genero = nuevoGenero;
        cmbGeneros.setSelectedItem(nuevoGenero);
        if (support != null) support.firePropertyChange(PROP_GENERO, old, nuevoGenero);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) { 
        if (support != null) support.addPropertyChangeListener(l); 
    }
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) { 
        if (support != null) support.removePropertyChangeListener(l); 
    }
}