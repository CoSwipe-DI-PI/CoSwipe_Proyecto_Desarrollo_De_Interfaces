package control;

import Vista.*;
import modelo.ModeloApp;
import modelo.PeliculaAPI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
/**
 * Controlador principal de la aplicación CoSwipe.
 *
 * <p>Conecta las pantallas de la capa de vista con el
 * {@link ModeloApp} y decide qué pantalla se muestra según la
 * acción del usuario. Implementa {@link ActionListener} para
 * reaccionar a los botones de las vistas.</p>
 *
 * <p>Gestiona estas pantallas:</p>
 * <ul>
 *   <li>Pantalla inicial (crear sala o unirse con PIN).</li>
 *   <li>Pantalla de carga.</li>
 *   <li>Pantalla de match.</li>
 *   <li>Pantallas de creación de sala.</li>
 * </ul>
 *
 * @author Sergio Garcia
 * @version 1.0
 * @see ModeloApp
 * @see ActionListener
 */
public class ControlApp implements ActionListener {

    private ModeloApp modelo;
    
    private PantallaInicial vistaInicial;
    private PantallaCarga vistaCarga;
    private MatchScreen vistaMatch;
    private CoSwipeMatchUI vistaCoSwipeMatch;
    private crearSalaPeliSerie vistaCrearPeliSerie;
    private crearSalaVideojuego vistaCrearVideojuego;

    /**
     * Se enlazan los botones con los Listener ,
     * cuando se clica un boton ocurren acciones
     * @param modelo 
     */
    public ControlApp(ModeloApp modelo) {
        this.modelo = modelo;
        
        this.vistaInicial = new PantallaInicial();
        this.vistaCarga = new PantallaCarga();
        this.vistaMatch = new MatchScreen();
        this.vistaCoSwipeMatch = new CoSwipeMatchUI();
        this.vistaCrearPeliSerie = new crearSalaPeliSerie();
        this.vistaCrearVideojuego = new crearSalaVideojuego();

        this.vistaInicial.getBtnCrearSala().addActionListener(this);
        this.vistaInicial.getBtnUnirseSala().addActionListener(this);
        
        this.vistaCrearPeliSerie.getBtnEmpezar().addActionListener(this);
        
        this.vistaMatch.getBtnMeGusta().addActionListener(this);
        this.vistaMatch.getBtnCerrar().addActionListener(this);
        
        this.vistaMatch.getLblLogo().addMouseListener(new MouseAdapter() {
            /**
             * Cuando se activa el evento pulsar el raton , volvemos al inicio
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAlInicio();
            }
        });

        this.vistaCoSwipeMatch.getLblLogo().addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAlInicio();
            }
        });

        this.vistaCoSwipeMatch.getLblCerrar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAlInicio();
            }
        });
    }
    /**
     * Cuando se inicia algo , ponemos visible la pantalla?
     */
    public void iniciar() {
        vistaInicial.setVisible(true);
    }

    private void volverAlInicio() {
        vistaMatch.setVisible(false);
        vistaCoSwipeMatch.setVisible(false);
        vistaCrearPeliSerie.setVisible(false);
        vistaCrearVideojuego.setVisible(false);
        vistaInicial.setVisible(true);
    }

    /**
     * Iniciamos las vistas?
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaInicial.getBtnUnirseSala()) {
            String pin = vistaInicial.getTxtPin();
            
            if (!modelo.validarPin(pin)) {
                vistaInicial.mostrarErrorPin();
                return; 
            }
            vistaInicial.setVisible(false);
            vistaCarga.setVisible(true);
            modelo.setParticipantes(5);
            vistaMatch.actualizarContadorParticipantes(modelo.getParticipantes());
            vistaCarga.setVisible(false);
            vistaMatch.setSize(1024, 768);
            vistaMatch.setLocationRelativeTo(null);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaInicial.getBtnCrearSala()) {
            vistaInicial.setVisible(false);
            vistaCrearPeliSerie.setVisible(true);
        }
        
        else if (e.getSource() == vistaCrearPeliSerie.getBtnEmpezar()) {
            vistaCrearPeliSerie.setVisible(false);
            vistaCarga.setVisible(true);
            
            try {
                String genero = vistaCrearPeliSerie.getGeneroSeleccionado();
                
                PeliculaAPI api = new PeliculaAPI();
                String[] datosPelicula = api.obtenerDatosPelicula(genero);
                
                vistaMatch.actualizarPelicula(datosPelicula[0], datosPelicula[1]);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar la pelicula.");
            }
            
            vistaCarga.setVisible(false);
            vistaMatch.setSize(1024, 768);
            vistaMatch.setLocationRelativeTo(null);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaMatch.getBtnMeGusta()) {
            vistaMatch.setVisible(false);
            vistaCoSwipeMatch.setSize(1280, 720);
            vistaCoSwipeMatch.setLocationRelativeTo(null);
            vistaCoSwipeMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaMatch.getBtnCerrar()) {
            volverAlInicio();
        }
    }
}