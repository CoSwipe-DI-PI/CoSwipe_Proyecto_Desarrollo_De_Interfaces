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
/**
 * Barra deslizante para elegir el número de jugadores de una sala (de 1 a 10).
 * <p>
 * Se arrastra la bolita con el ratón y debajo se muestra el valor
 * ("10+" cuando llega al máximo). Cada cambio se notifica con la
 * propiedad {@link #PROP_JUGADORES}.
 *
 * @author Lucia Lopez Alonso
 * @version 1.0
 * @since 2.0
 * @see java.beans.PropertyChangeListener
 */
public class BarraJugador extends JPanel implements Serializable {

     /** Nombre de la propiedad que se notifica al cambiar el número de jugadores. */
    public static final String PROP_JUGADORES = "jugadores";

    public static final int MIN_JUGADORES = 1;
    public static final int MAX_JUGADORES = 10;

    private static final int MARGEN = 12;
    private static final int RADIO = 16;
    private static final int GROSOR = 6;
    private static final int ALTO_TEXTO = 20;
    private static final Color COLOR_FONDO_BARRA = new Color(200, 200, 200);
    private static final Color COLOR_ACENTO = new Color(0x7C, 0x3A, 0xED);

    
    private transient PropertyChangeSupport propertySupport;
    private int jugadores = 2;
    private transient boolean arrastrando;

    /**
     * Crea la barra con 2 jugadores, un tamaño preferido de 420x60
     * y el control del ratón para arrastrar la bolita.
     */
    public BarraJugador() {
        propertySupport = new PropertyChangeSupport(this);
        setOpaque(false);
        setPreferredSize(new Dimension(420, 60));

        MouseAdapter raton = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                arrastrando = true;
                moverA(e.getX());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                moverA(e.getX());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                arrastrando = false;
            }
        };
        addMouseListener(raton);
        addMouseMotionListener(raton);
    }

    // --- Propiedad ---

    /**
     * Devuelve el número de jugadores seleccionado.
     *
     * @return número de jugadores, entre {@link #MIN_JUGADORES} y {@link #MAX_JUGADORES}
     */
    public int getJugadores() {
        return jugadores;
    }

    /**
     * Cambia el número de jugadores, repinta la barra y lanza el evento
     * {@link #PROP_JUGADORES}. Los valores fuera del rango se ajustan al límite.
     *
     * @param nuevo nuevo número de jugadores
     */
    public void setJugadores(int nuevo) {
        int anterior = jugadores;
        jugadores = Math.max(MIN_JUGADORES, Math.min(MAX_JUGADORES, nuevo));
        if (jugadores != anterior) {
            repaint();
            propertySupport.firePropertyChange(PROP_JUGADORES, anterior, jugadores);
        }
    }

    // --- Ratón ---

    /**
     * Mueve la bolita a la posición del ratón si se está arrastrando.
     *
     * @param x posición horizontal del ratón en píxeles
     */
    private void moverA(int x) {
        if (!arrastrando) {
            return;
        }
        setJugadores(aValor(x));
    }

    /**
     * Convierte un número de jugadores en su posición en píxeles sobre la barra.
     *
     * @param valor número de jugadores
     * @return posición horizontal en píxeles
     */
    private int aPixel(int valor) {
        int ancho = getWidth() - 2 * MARGEN;
        return MARGEN + (valor - MIN_JUGADORES) * ancho / (MAX_JUGADORES - MIN_JUGADORES);
    }

    /**
     * Convierte una posición en píxeles en el número de jugadores más cercano.
     *
     * @param px posición horizontal en píxeles
     * @return número de jugadores correspondiente
     */
    private int aValor(int px) {
        int ancho = Math.max(1, getWidth() - 2 * MARGEN);
        return MIN_JUGADORES + Math.round((px - MARGEN) * (float) (MAX_JUGADORES - MIN_JUGADORES) / ancho);
    }

    // --- Dibujo ---

    /**
     * Dibuja la barra gris, la parte rellena en morado, la bolita y la etiqueta.
     *
     * @param g contexto gráfico donde se dibuja
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int yBarra = (getHeight() - ALTO_TEXTO) / 2;
        int px = aPixel(jugadores);

        g2.setColor(COLOR_FONDO_BARRA);
        g2.fillRoundRect(MARGEN, yBarra - GROSOR / 2, getWidth() - 2 * MARGEN, GROSOR, GROSOR, GROSOR);

        g2.setColor(COLOR_ACENTO);
        g2.fillRoundRect(MARGEN, yBarra - GROSOR / 2, px - MARGEN, GROSOR, GROSOR, GROSOR);
        g2.fillOval(px - RADIO / 2, yBarra - RADIO / 2, RADIO, RADIO);

        dibujarEtiqueta(g2, px);

        g2.dispose();
    }

    /**
     * Dibuja el texto "N jugadores" centrado bajo la bolita,
     * sin que se salga del panel.
     *
     * @param g2 contexto gráfico
     * @param px posición horizontal de la bolita
     */
    private void dibujarEtiqueta(Graphics2D g2, int px) {
        String numero = (jugadores == MAX_JUGADORES) ? MAX_JUGADORES + "+" : String.valueOf(jugadores);
        String texto = numero + " jugadores";
        FontMetrics fm = g2.getFontMetrics();
        int x = px - fm.stringWidth(texto) / 2;
        x = Math.max(0, Math.min(getWidth() - fm.stringWidth(texto), x));
        g2.setColor(COLOR_ACENTO);
        g2.drawString(texto, x, getHeight() - 4);
    }

    // --- Eventos ---

    /** {@inheritDoc} */
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