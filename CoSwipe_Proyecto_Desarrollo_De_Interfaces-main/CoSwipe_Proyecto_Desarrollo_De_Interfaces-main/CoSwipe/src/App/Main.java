package app;

import control.ControlApp;
import modelo.ModeloApp;

public class Main {

    public static void main(String[] args) {
        
        ModeloApp modelo = new ModeloApp();
        
        ControlApp controlador = new ControlApp(modelo);
        
        controlador.iniciar();
    }
}