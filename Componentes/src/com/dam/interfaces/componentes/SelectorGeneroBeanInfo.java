package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class SelectorGeneroBeanInfo extends SimpleBeanInfo {
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(SelectorGenero.class);
        descriptor.setDisplayName("Selector de Género");
        descriptor.setShortDescription("Desplegable para elegir género.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propGenero = new PropertyDescriptor("genero", SelectorGenero.class, "getGenero", "setGenero");
            propGenero.setDisplayName("Género");
            propGenero.setPreferred(true);
            return new PropertyDescriptor[] { propGenero };
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}