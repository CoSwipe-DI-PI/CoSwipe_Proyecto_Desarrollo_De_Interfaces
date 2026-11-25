package com.dam.interfaces.componentes;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * PLACEHOLDER-----
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
 * @see JString,JTextField,JPanel,Serializable
 */
public class CampoPin extends JPanel implements Serializable {
    public static final String PROP_PIN_COMPLETO = "pinCompleto";
    private PropertyChangeSupport support;
    private JTextField txtPin;
    private String pinCompleto;
    private Color colorNormal = Color.WHITE;
    private Color colorError = new Color(255, 204, 204); // Rojo clarito

    public CampoPin() {
        support = new PropertyChangeSupport(this);
        this.pinCompleto = "";
        inicializarInterfaz();
        setPreferredSize(new java.awt.Dimension(150, 30));
    }

    private void inicializarInterfaz() {
        txtPin = new JTextField(6); // 6 columnas visuales
        txtPin.setHorizontalAlignment(JTextField.CENTER);
        txtPin.setFont(new java.awt.Font("Monospaced", 1, 18));
        this.add(txtPin);

        // 1. Filtro para que SOLO se puedan escribir números y MÁXIMO 6
        ((AbstractDocument) txtPin.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("\\d+") && (fb.getDocument().getLength() + string.length()) <= 6) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && text.matches("\\d+") && (fb.getDocument().getLength() - length + text.length()) <= 6) {
                    super.replace(fb, offset, length, text, attrs);
                } else if (text != null && text.isEmpty()) {
                    // Permitir borrar
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // 2. Listener para comprobar si tiene 6 dígitos y cambiar el color
        txtPin.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validar(); }
            public void removeUpdate(DocumentEvent e) { validar(); }
            public void changedUpdate(DocumentEvent e) { validar(); }
        });
        
        // Ponemos el fondo rojo al empezar, porque está vacío (menos de 6)
        txtPin.setBackground(colorError); 
    }

    private void validar() {
        String texto = txtPin.getText().trim();
        String old = this.pinCompleto;

        if (texto.length() == 6) {
            this.pinCompleto = texto;
            txtPin.setBackground(colorNormal); // Se pone blanco si llega a 6
        } else {
            this.pinCompleto = "";
            txtPin.setBackground(colorError);  // Se queda rojo si tiene menos
        }

        if (support != null) {
            support.firePropertyChange(PROP_PIN_COMPLETO, old, this.pinCompleto);
        }
    }

    public JTextField getTxtPin() { 
        return txtPin; 
    }
    
    public String getPinCompleto() { 
        return pinCompleto; 
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