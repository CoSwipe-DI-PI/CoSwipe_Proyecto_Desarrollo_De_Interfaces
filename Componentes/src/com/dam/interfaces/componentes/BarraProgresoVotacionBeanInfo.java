package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BarraProgresoVotacionBeanInfo extends SimpleBeanInfo {
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(BarraProgresoVotacion.class);
        descriptor.setDisplayName("Barra de Progreso");
        descriptor.setShortDescription("Barra que indica el progreso de la votación.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propProgreso = new PropertyDescriptor("progreso", BarraProgresoVotacion.class, "getProgreso", "setProgreso");
            propProgreso.setDisplayName("Progreso");
            propProgreso.setPreferred(true);
            
            PropertyDescriptor propCompletado = new PropertyDescriptor("completado", BarraProgresoVotacion.class, "isCompletado", null);
            propCompletado.setDisplayName("Completado");
            propCompletado.setPreferred(true);
            
            return new PropertyDescriptor[] { propProgreso, propCompletado };
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}