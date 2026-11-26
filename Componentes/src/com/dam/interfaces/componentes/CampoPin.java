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
 * Campo de texto para introducir el PIN de 6 dígitos de una sala de CoSwipe.
 * <p>
 * Solo admite números y un máximo de 6 caracteres. El fondo se muestra
 * en rojo mientras el PIN está incompleto y en blanco cuando tiene 6 dígitos.
 * Cada cambio se notifica mediante la propiedad {@link #PROP_PIN_COMPLETO}.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see CampoPinBeanInfo
 * @see java.beans.PropertyChangeListener
 */
public class CampoPin extends JPanel implements Serializable {

    public static final String PROP_PIN_COMPLETO = "pinCompleto";
    private PropertyChangeSupport support;
    private JTextField txtPin;
    private String pinCompleto;
    private Color colorNormal = Color.WHITE;
    private Color colorError = new Color(255, 204, 204); // Rojo clarito

    /**
     * Crea el campo vacio , con el fondo de error y con dimension 150 x 30. 
     */
    public CampoPin() {
        support = new PropertyChangeSupport(this);
        this.pinCompleto = "";
        inicializarInterfaz();
        setPreferredSize(new java.awt.Dimension(150, 30));
    }
    
     /**
     * Crea el campo de texto, le aplica el filtro de solo números
     * (máximo 6) y registra el oyente que valida el PIN en cada cambio.
     */
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
            public void insertUpdate(DocumentEvent e) {
                validar();
            }

            public void removeUpdate(DocumentEvent e) {
                validar();
            }

            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });

        // Ponemos el fondo rojo al empezar, porque está vacío (menos de 6)
        txtPin.setBackground(colorError);
    }
    
     /**
     * Comprueba si el texto tiene 6 dígitos, actualiza el color de fondo
     * y el valor de {@code pinCompleto}, y lanza el evento
     * {@link #PROP_PIN_COMPLETO}.
     */
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

    /**
     * {@inheritDoc}
     * Se usa para escuchar los cambios de {@link #PROP_PIN_COMPLETO}.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.addPropertyChangeListener(l);
        }
    }

     /** {@inheritDoc} */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        if (support != null) {
            support.removePropertyChangeListener(l);
        }
    }
}
