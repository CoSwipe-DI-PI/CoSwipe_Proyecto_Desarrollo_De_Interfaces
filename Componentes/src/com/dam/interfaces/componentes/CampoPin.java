package com.dam.interfaces.componentes;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CampoPin extends JPanel {

    private JTextField campoTexto;

    public CampoPin() {
        campoTexto = new JTextField(6);
        campoTexto.setHorizontalAlignment(JTextField.CENTER);
        campoTexto.setBackground(Color.WHITE); // Al principio es blanco (vacío)
        add(campoTexto);

        campoTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char letra = e.getKeyChar();
                if (!Character.isDigit(letra) || campoTexto.getText().length() >= 6) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                comprobarColor();
            }
        });
    }

    private void comprobarColor() {
        int longitud = campoTexto.getText().length();
        
        if (longitud == 0) {
            campoTexto.setBackground(Color.WHITE); // Vacío = Blanco
        } else if (longitud > 0 && longitud < 6) {
            campoTexto.setBackground(new Color(255, 204, 204)); // 1,2,3,4,5 = Rojo
        } else if (longitud == 6) {
            campoTexto.setBackground(Color.WHITE); // 6 = Blanco
        }
    }

    public JTextField getCampoTexto() {
        return campoTexto;
    }

    public String getPinCompleto() {
        if (campoTexto.getText().length() == 6) {
            return campoTexto.getText();
        }
        return "";
    }
}