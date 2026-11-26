package com.dam.interfaces.componentes;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonLikeDislike extends JPanel {

    private JButton botonLike;
    private JButton botonDislike;
    private String voto;

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

    public String getVoto() {
        return voto;
    }

    public void setVoto(String nuevoVoto) {
        this.voto = nuevoVoto;
    }
}