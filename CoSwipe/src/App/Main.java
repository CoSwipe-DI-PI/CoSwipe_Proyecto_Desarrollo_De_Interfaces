package app;

import control.ControlApp;
import modelo.ModeloApp;

/**
 * Pantalla de entrada a la App
 * 
 * <p>Conecta el modelo con el controlador</p>
 * @author Sergio Garcia
 * @version 1.0
 * @see ModeloApp
 * @see ControlApp
 */
public class Main {
    public static void main(String[] args) {
        ModeloApp modelo = new ModeloApp();
        ControlApp controlador = new ControlApp(modelo);
        controlador.iniciar();
    }
}