package control;

import Vista.*;
import modelo.ModeloApp;
import modelo.PeliculaAPI;
import modelo.VideojuegosAPI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ControlApp implements ActionListener {

    private ModeloApp modelo;
    
    private PantallaInicial vistaInicial;
    private PantallaCarga vistaCarga;
    private MatchScreen vistaMatch;
    private CoSwipeMatchUI vistaCoSwipeMatch;
    private crearSalaPeliSerie vistaCrearPeliSerie;
    private crearSalaVideojuego vistaCrearVideojuego;

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
        this.vistaCrearPeliSerie.getBtnCatGames().addActionListener(this); 
        this.vistaCrearVideojuego.getBtnEmpezar().addActionListener(this);
        
        this.vistaCrearVideojuego.getBtnCatMovies().addActionListener(this);
        this.vistaCrearVideojuego.getBtnCatSeries().addActionListener(this);
        
        this.vistaMatch.getBtnMeGusta().addActionListener(this);
        this.vistaMatch.getBtnCerrar().addActionListener(this);
        
        this.vistaMatch.getLblLogo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlInicio(); }
        });

        this.vistaCoSwipeMatch.getLblLogo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlInicio(); }
        });

        this.vistaCoSwipeMatch.getLblCerrar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlInicio(); }
        });
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaInicial.getBtnUnirseSala()) {
            String pin = vistaInicial.getTxtPin();
            if (!modelo.validarPin(pin)) {
                vistaInicial.mostrarErrorPin();
                return; 
            }
            vistaInicial.setVisible(false);
            
            vistaCarga.setSize(1024, 768);
            vistaCarga.setLocationRelativeTo(null);
            vistaCarga.setVisible(true);
            
            modelo.setParticipantes(5);
            vistaMatch.actualizarContadorParticipantes(modelo.getParticipantes());
            
            try { Thread.sleep(1500); } catch (InterruptedException ex) {}
            
            vistaCarga.setVisible(false);
            vistaMatch.setSize(1024, 768);
            vistaMatch.setLocationRelativeTo(null);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaInicial.getBtnCrearSala()) {
            vistaInicial.setVisible(false);
            vistaCrearPeliSerie.setSize(1024, 768);
            vistaCrearPeliSerie.setLocationRelativeTo(null);
            vistaCrearPeliSerie.setVisible(true);
        }
        
        else if (e.getSource() == vistaCrearPeliSerie.getBtnCatGames()) {
            vistaCrearPeliSerie.setVisible(false);
            vistaCrearVideojuego.setSize(1024, 768);
            vistaCrearVideojuego.setLocationRelativeTo(null);
            vistaCrearVideojuego.setVisible(true);
        }
        
        else if (e.getSource() == vistaCrearVideojuego.getBtnCatMovies() || 
                 e.getSource() == vistaCrearVideojuego.getBtnCatSeries()) {
            vistaCrearVideojuego.setVisible(false);
            vistaCrearPeliSerie.setSize(1024, 768);
            vistaCrearPeliSerie.setLocationRelativeTo(null);
            vistaCrearPeliSerie.setVisible(true);
        }
        
        // EMPEZAR PELÍCULAS O SERIES
        else if (e.getSource() == vistaCrearPeliSerie.getBtnEmpezar()) {
            vistaCrearPeliSerie.setVisible(false);
            
            vistaCarga.setSize(1024, 768);
            vistaCarga.setLocationRelativeTo(null);
            vistaCarga.setVisible(true);
            
            try {
                String genero = vistaCrearPeliSerie.getGeneroSeleccionado();
                
                // COMPROBAMOS QUÉ BOTÓN ESTÁ SELECCIONADO
                String tipo = "Película"; 
                if (vistaCrearPeliSerie.isSerieSeleccionada()) {
                    tipo = "Serie";
                }
                
                PeliculaAPI api = new PeliculaAPI();
                // Le pasamos el tipo a la API
                String[] datos = api.obtenerDatos(genero, tipo);
                
                String titulo = datos[0];
                String urlImagen = datos[1];
                String nota = datos[2];
                
                modelo.setTituloActual(titulo);
                modelo.setDetallesActuales(tipo + " · Streaming");
                modelo.setImagenActual(urlImagen);
                
                vistaMatch.actualizarContenido(titulo, tipo + " · Streaming", nota, "Streaming", urlImagen);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar contenido.");
            }
            
            try { Thread.sleep(2500); } catch (InterruptedException ex) {}
            
            vistaCarga.setVisible(false);
            vistaMatch.setSize(1024, 768);
            vistaMatch.setLocationRelativeTo(null);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaCrearVideojuego.getBtnEmpezar()) {
            vistaCrearVideojuego.setVisible(false);
            
            vistaCarga.setSize(1024, 768);
            vistaCarga.setLocationRelativeTo(null);
            vistaCarga.setVisible(true);
            
            try {
                String generoJuego = vistaCrearVideojuego.getGeneroJuegoSeleccionado();
                String plataformaJuego = vistaCrearVideojuego.getPlataformaSeleccionada();
                
                VideojuegosAPI api = new VideojuegosAPI();
                String[] datosJuego = api.obtenerJuegoAleatorio(generoJuego);
                
                String urlImagen = datosJuego[4];
                if (urlImagen == null || urlImagen.equals("")) {
                    urlImagen = "";
                }
                
                modelo.setTituloActual(datosJuego[0]);
                modelo.setDetallesActuales(plataformaJuego); 
                modelo.setImagenActual(urlImagen);
                
                vistaMatch.actualizarContenido(datosJuego[0], datosJuego[1], datosJuego[2], plataformaJuego, urlImagen);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar videojuego.");
            }
            
            try { Thread.sleep(2500); } catch (InterruptedException ex) {}
            
            vistaCarga.setVisible(false);
            vistaMatch.setSize(1024, 768);
            vistaMatch.setLocationRelativeTo(null);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaMatch.getBtnMeGusta()) {
            vistaMatch.setVisible(false);
            
            vistaCarga.setSize(1024, 768);
            vistaCarga.setLocationRelativeTo(null);
            vistaCarga.setVisible(true);
            
            vistaCoSwipeMatch.actualizarResultadoFinal(
                modelo.getTituloActual(), 
                modelo.getDetallesActuales(), 
                modelo.getImagenActual(),
                modelo.getDetallesActuales()
            );
            
            try { Thread.sleep(1500); } catch (InterruptedException ex) {}
            
            vistaCarga.setVisible(false);
            vistaCoSwipeMatch.setSize(1280, 720);
            vistaCoSwipeMatch.setLocationRelativeTo(null);
            vistaCoSwipeMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaMatch.getBtnCerrar()) {
            volverAlInicio();
        }
    }
}