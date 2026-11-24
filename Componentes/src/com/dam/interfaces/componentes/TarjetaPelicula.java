package com.dam.interfaces.componentes;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TarjetaPelicula extends JPanel implements Serializable {
    public static final String PROP_TITULO = "titulo";
    public static final String PROP_SINOPSIS = "sinopsis";
    private PropertyChangeSupport support;
    private JLabel lblTitulo;
    private JLabel lblSinopsis;
    private String titulo;
    private String sinopsis;

    public TarjetaPelicula() {
        support = new PropertyChangeSupport(this);
        this.titulo = "Sin título";
        this.sinopsis = "Sin descripción";
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new java.awt.Color(30, 30, 40));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 80, 100)));
        
        lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        
        lblSinopsis = new JLabel(sinopsis, SwingConstants.CENTER);
        lblSinopsis.setForeground(java.awt.Color.LIGHT_GRAY);

        add(lblTitulo, BorderLayout.NORTH);
        add(lblSinopsis, BorderLayout.CENTER);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String nuevoTitulo) {
        String old = this.titulo;
        this.titulo = nuevoTitulo;
        lblTitulo.setText(nuevoTitulo);
        if (support != null) support.firePropertyChange(PROP_TITULO, old, nuevoTitulo);
    }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String nuevaSinopsis) {
        String old = this.sinopsis;
        this.sinopsis = nuevaSinopsis;
        lblSinopsis.setText(nuevaSinopsis);
        if (support != null) support.firePropertyChange(PROP_SINOPSIS, old, nuevaSinopsis);
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