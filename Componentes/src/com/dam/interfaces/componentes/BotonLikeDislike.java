package com.dam.interfaces.componentes;

import java.awt.GridLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Componente visual que contiene dos {@link javax.swing.JButton}
 * ("Me gusta" y "No me gusta") usado en {@code MatchScreen}
 * para votar el contenido mostrado.
 * <p>
 * Notifica la elección mediante eventos para que el
 * controlador (MVC) registre el voto en la sala.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see BotonLikeDislikeBeanInfo
 * @see java.beans.PropertyChangeListener
 * 
 */
public class BotonLikeDislike extends JPanel implements Serializable {

    /**
     * Nombre de la propiedad que se notifica al cambiar 
     * el voto
     */
    public static final String PROP_VOTO = "voto";
    private PropertyChangeSupport support;
    private JButton btnLike;
    private JButton btnDislike;
    private String voto;

    public BotonLikeDislike() {
        // El support se inicializa siempre primero para evitar nulos
        support = new PropertyChangeSupport(this);
        this.voto = "";
        inicializarInterfaz();
    }

    /**
    * 
    * Construye y coloca los botones 
    * y registra sus acciones.
    * Cada pulsacion cambia el voto y lanza un evento 
    * {@link #PROP_VOTO].
    */
    private void inicializarInterfaz() {
        setLayout(new GridLayout(1, 2, 10, 0));

        btnLike = new JButton("♡ LIKE");
        btnLike.setBackground(new java.awt.Color(34, 197, 94));
        btnLike.setForeground(java.awt.Color.WHITE);

        btnDislike = new JButton("✕ PASS");
        btnDislike.setBackground(new java.awt.Color(239, 68, 68));
        btnDislike.setForeground(java.awt.Color.WHITE);

        add(btnDislike);
        add(btnLike);

        btnLike.addActionListener(e -> {
            String old = this.voto;
            this.voto = "LIKE";
            if (support != null) {
                support.firePropertyChange(PROP_VOTO, old, this.voto);
            }
        });

        btnDislike.addActionListener(e -> {
            String old = this.voto;
            this.voto = "DISLIKE";
            if (support != null) {
                support.firePropertyChange(PROP_VOTO, old, this.voto);
            }
        });
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        String old = this.voto;
        this.voto = voto;
        if (support != null) {
            support.firePropertyChange(PROP_VOTO, old, voto);
        }
    }
    
    /**
     * {@inheritDoc}
     * Se usa para escuchar los cambios de
     * {@link #PROP_VOTO}  
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.addPropertyChangeListener(l);
        }
    }

    /**
     * 
     * {@inheritDoc } 
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.removePropertyChangeListener(l);
        }
    }
}
