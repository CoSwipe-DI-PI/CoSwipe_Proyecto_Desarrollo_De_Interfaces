package com.dam.interfaces.componentes;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Componente visual con dos botones, "LIKE" y "PASS", usado en
 * {@code MatchScreen} para votar el contenido mostrado.
 * <p>
 * Al pulsar un botón se guarda el voto ("LIKE" o "DISLIKE"), que el
 * controlador consulta con {@link #getVoto()}.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see BotonLikeDislikeBeanInfo
 */
public class BotonLikeDislike extends JPanel {

    private JButton botonLike;
    private JButton botonDislike;
    private String voto;

    /**
     * Crea el componente con el voto vacío y los botones
     * "PASS" (rojo) y "LIKE" (verde).
     */
    public BotonLikeDislike() {
        this.voto = "";
        setLayout(new GridLayout(1, 2, 10, 0));

        botonLike = new JButton("♡ LIKE");
        botonLike.setBackground(new java.awt.Color(34, 197, 94));
        botonLike.setForeground(java.awt.Color.WHITE);
        
        botonDislike = new JButton("✕ PASS");
        botonDislike.setBackground(new java.awt.Color(239, 68, 68));
        botonDislike.setForeground(java.awt.Color.WHITE);

        add(botonDislike);
        add(botonLike);

        botonLike.addActionListener(e -> this.voto = "LIKE");
        botonDislike.addActionListener(e -> this.voto = "DISLIKE");
    }

    /**
     * Devuelve el último voto registrado.
     *
     * @return "LIKE", "DISLIKE" o cadena vacía si aún no se ha votado
     */
    public String getVoto() {
        return voto;
    }

    /**
     * Cambia el voto actual, por ejemplo para reiniciarlo con "".
     *
     * @param nuevoVoto nuevo valor del voto
     */
    public void setVoto(String nuevoVoto) {
        this.voto = nuevoVoto;
    }
}