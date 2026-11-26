package com.dam.interfaces.componentes;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Campo de texto para introducir el PIN de 6 dígitos de una sala de CoSwipe.
 * <p>
 * Solo admite números y un máximo de 6 caracteres. El fondo es blanco
 * si está vacío o completo, y rojo mientras el PIN está a medias.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see CampoPinBeanInfo
 */
public class CampoPin extends JPanel {

    private JTextField campoTexto;

    /**
     * Crea el campo vacío y registra el control de teclas,
     * que solo deja escribir dígitos hasta un máximo de 6.
     */
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

     /**
     * Cambia el color de fondo según cuántos dígitos se han escrito.
     */
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