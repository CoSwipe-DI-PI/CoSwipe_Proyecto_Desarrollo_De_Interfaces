package control;

import Vista.CoSwipeMatchUI;
import Vista.MatchScreen;
import Vista.PantallaCarga;
import Vista.PantallaInicial;
import Vista.crearSalaPeliSerie;
import Vista.crearSalaVideojuego;
import modelo.ModeloApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.vistaCrearVideojuego.getBtnEmpezar().addActionListener(this);
        this.vistaMatch.getBtnMeGusta().addActionListener(this);
    }

    public void iniciar() {
        vistaInicial.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaInicial.getBtnUnirseSala()) {
            vistaInicial.setVisible(false);
            vistaCarga.setVisible(true);
            vistaCarga.setVisible(false);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaInicial.getBtnCrearSala()) {
            vistaInicial.setVisible(false);
            vistaCrearPeliSerie.setVisible(true);
        }

        else if (e.getSource() == vistaCrearPeliSerie.getBtnEmpezar()) {
            vistaCrearPeliSerie.setVisible(false);
            vistaCarga.setVisible(true);
            vistaCarga.setVisible(false);
            vistaMatch.setVisible(true);
        }
        
        else if (e.getSource() == vistaCrearVideojuego.getBtnEmpezar()) {
            vistaCrearVideojuego.setVisible(false);
            vistaCarga.setVisible(true);
            vistaCarga.setVisible(false);
            vistaMatch.setVisible(true); 
        }

        else if (e.getSource() == vistaMatch.getBtnMeGusta()) {
            vistaMatch.setVisible(false);
            vistaCoSwipeMatch.setVisible(true);
        }
    }
}