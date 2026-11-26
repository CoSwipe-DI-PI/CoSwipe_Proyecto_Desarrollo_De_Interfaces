package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Descriptor para integrar BarraDinero en el diseñador visual Matisse.
 * 
 * @author Lucia Lopez Alonso
 */
public class BarraDineroBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(BarraDinero.class);
        descriptor.setDisplayName("Barra de Rango de Precio");
        descriptor.setShortDescription("Barra deslizante doble para elegir precio mínimo y máximo.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // Definimos la propiedad "minimo"
            PropertyDescriptor propMinimo = new PropertyDescriptor(
                    "minimo", 
                    BarraDinero.class, 
                    "getMinimo", 
                    "setMinimo"
            );
            propMinimo.setDisplayName("Precio Mínimo");
            propMinimo.setShortDescription("Valor mínimo del rango de precio (€).");
            propMinimo.setPreferred(true);

            // Definimos la propiedad "maximo"
            PropertyDescriptor propMaximo = new PropertyDescriptor(
                    "maximo", 
                    BarraDinero.class, 
                    "getMaximo", 
                    "setMaximo"
            );
            propMaximo.setDisplayName("Precio Máximo");
            propMaximo.setShortDescription("Valor máximo del rango de precio (€).");
            propMaximo.setPreferred(true);
            
            // Devolvemos el array con las dos propiedades
            return new PropertyDescriptor[]{ propMinimo, propMaximo };
            
        } catch (IntrospectionException e) {
            // Si hay algún error, devolvemos el estándar
            return super.getPropertyDescriptors();
        }
    }
}