package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class TarjetaPeliculaBeanInfo extends SimpleBeanInfo {
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(TarjetaPelicula.class);
        descriptor.setDisplayName("Tarjeta Película");
        descriptor.setShortDescription("Muestra el título y sinopsis de una película.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propTitulo = new PropertyDescriptor("titulo", TarjetaPelicula.class, "getTitulo", "setTitulo");
            propTitulo.setDisplayName("Título");
            propTitulo.setPreferred(true);

            PropertyDescriptor propSinopsis = new PropertyDescriptor("sinopsis", TarjetaPelicula.class, "getSinopsis", "setSinopsis");
            propSinopsis.setDisplayName("Sinopsis");
            propSinopsis.setPreferred(true);
            
            return new PropertyDescriptor[] { propTitulo, propSinopsis };
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}