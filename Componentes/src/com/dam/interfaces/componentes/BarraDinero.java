package com.dam.interfaces.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JPanel;

public class BarraDinero extends JPanel implements Serializable {

    public static final String PROP_MINIMO = "minimo";
    public static final String PROP_MAXIMO = "maximo";

    public static final int VALOR_MIN = 0;
    public static final int VALOR_MAX = 100;
    private static final int PASO = 5;
    private static final int MARGEN = 12;
    private static final int RADIO = 16;
    private static final int GROSOR = 6;
    private static final int ALTO_TEXTO = 20;
    private static final int ESPACIO = 8; // separación mínima entre las dos etiquetas
    private static final Color COLOR_FONDO_BARRA = new Color(200, 200, 200);
    private static final Color COLOR_ACENTO = new Color(0x7C, 0x3A, 0xED);

    private transient PropertyChangeSupport propertySupport;
    private int minimo = 20;
    private int maximo = 80;
    private transient int arrastrando; // 0 = nada, 1 = mínimo, 2 = máximo

    public BarraDinero() {
        propertySupport = new PropertyChangeSupport(this);
        setOpaque(false);
        setPreferredSize(new Dimension(420, 60));

        MouseAdapter raton = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int pxMin = aPixel(minimo);
                int pxMax = aPixel(maximo);
                int x = e.getX();
                if (x < pxMin) {
                    arrastrando = 1;
                } else if (x > pxMax) {
                    arrastrando = 2;
                } else {
                    arrastrando = (x - pxMin <= pxMax - x) ? 1 : 2;
                }
                moverA(x);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                moverA(e.getX());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                arrastrando = 0;
            }
        };
        addMouseListener(raton);
        addMouseMotionListener(raton);
    }

    // --- Propiedades ---

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int nuevo) {
        int anterior = minimo;
        minimo = Math.max(VALOR_MIN, Math.min(nuevo, maximo));
        if (minimo != anterior) {
            repaint();
            propertySupport.firePropertyChange(PROP_MINIMO, anterior, minimo);
        }
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int nuevo) {
        int anterior = maximo;
        maximo = Math.min(VALOR_MAX, Math.max(nuevo, minimo));
        if (maximo != anterior) {
            repaint();
            propertySupport.firePropertyChange(PROP_MAXIMO, anterior, maximo);
        }
    }

    // --- Ratón ---

    private void moverA(int x) {
        if (arrastrando == 0) {
            return;
        }
        int valor = aValor(x);
        if (arrastrando == 1) {
            setMinimo(valor);
        } else {
            setMaximo(valor);
        }
    }

    private int aPixel(int valor) {
        int ancho = getWidth() - 2 * MARGEN;
        return MARGEN + (valor - VALOR_MIN) * ancho / (VALOR_MAX - VALOR_MIN);
    }

    private int aValor(int px) {
        int ancho = Math.max(1, getWidth() - 2 * MARGEN);
        int valor = VALOR_MIN + (px - MARGEN) * (VALOR_MAX - VALOR_MIN) / ancho;
        valor = Math.round(valor / (float) PASO) * PASO;
        return Math.max(VALOR_MIN, Math.min(VALOR_MAX, valor));
    }

    // --- Dibujo ---

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int yBarra = (getHeight() - ALTO_TEXTO) / 2;
        int pxMin = aPixel(minimo);
        int pxMax = aPixel(maximo);

        g2.setColor(COLOR_FONDO_BARRA);
        g2.fillRoundRect(MARGEN, yBarra - GROSOR / 2, getWidth() - 2 * MARGEN, GROSOR, GROSOR, GROSOR);

        g2.setColor(COLOR_ACENTO);
        g2.fillRect(pxMin, yBarra - GROSOR / 2, pxMax - pxMin, GROSOR);

        g2.fillOval(pxMin - RADIO / 2, yBarra - RADIO / 2, RADIO, RADIO);
        g2.fillOval(pxMax - RADIO / 2, yBarra - RADIO / 2, RADIO, RADIO);

        dibujarEtiquetas(g2, pxMin, pxMax);

        g2.dispose();
    }

    private void dibujarEtiquetas(Graphics2D g2, int pxMin, int pxMax) {
        FontMetrics fm = g2.getFontMetrics();
        String textoMin = textoValor(minimo);
        String textoMax = textoValor(maximo);
        int anchoMin = fm.stringWidth(textoMin);
        int anchoMax = fm.stringWidth(textoMax);

        // Posición centrada bajo cada bolita
        int xMin = pxMin - anchoMin / 2;
        int xMax = pxMax - anchoMax / 2;

        // Si se solapan, el mínimo va a la izquierda de su bolita y el máximo a la derecha de la suya
        if (xMin + anchoMin + ESPACIO > xMax) {
            xMin = pxMin - anchoMin - ESPACIO;
            xMax = pxMax + ESPACIO;
        }

        // Que no se salgan del panel y que nunca se toquen
        xMin = Math.max(0, Math.min(getWidth() - anchoMin, xMin));
        xMax = Math.max(xMax, xMin + anchoMin + ESPACIO);
        xMax = Math.max(0, Math.min(getWidth() - anchoMax, xMax));

        g2.setColor(COLOR_ACENTO);
        g2.drawString(textoMin, xMin, getHeight() - 4);
        g2.drawString(textoMax, xMax, getHeight() - 4);
    }

    private String textoValor(int valor) {
        String numero = (valor == VALOR_MAX) ? VALOR_MAX + "+" : String.valueOf(valor);
        return numero + " €";
    }

    // --- Eventos ---

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.addPropertyChangeListener(listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.removePropertyChangeListener(listener);
        }
    }

    @Override
    public void addPropertyChangeListener(String propiedad, PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.addPropertyChangeListener(propiedad, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(String propiedad, PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.removePropertyChangeListener(propiedad, listener);
        }
    }
}