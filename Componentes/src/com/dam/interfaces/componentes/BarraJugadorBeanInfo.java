package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Descriptor para integrar BarraJugador en el diseñador visual Matisse.
 * 
 * @author Lucia Lopez Alonso
 */
public class BarraJugadorBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(BarraJugador.class);
        descriptor.setDisplayName("Barra Seleccionadora de Jugadores");
        descriptor.setShortDescription("Barra deslizante para elegir de 1 a 10 jugadores.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // Definimos la propiedad "jugadores" indicando el getter y el setter
            PropertyDescriptor propJugadores = new PropertyDescriptor(
                    "jugadores", 
                    BarraJugador.class, 
                    "getJugadores", 
                    "setJugadores"
            );
            
            // Le ponemos un nombre legible para la ventana de propiedades de NetBeans
            propJugadores.setDisplayName("Número de Jugadores");
            propJugadores.setShortDescription("Cantidad de jugadores en la sala (1-10).");
            propJugadores.setPreferred(true); // Aparece destacado en la ventana Properties
            
            return new PropertyDescriptor[]{ propJugadores };
            
        } catch (IntrospectionException e) {
            // Si hay algún error con los nombres de los métodos, devolvemos el estándar
            return super.getPropertyDescriptors();
        }
    }
}