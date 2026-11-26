package app;

import control.ControlApp;
import modelo.ModeloApp;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");        
        ModeloApp modelo = new ModeloApp();
        ControlApp controlador = new ControlApp(modelo);
        controlador.iniciar();
    }
}