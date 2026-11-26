package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BotonLikeDislikeBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(BotonLikeDislike.class);
        descriptor.setDisplayName("Botones Like/Dislike");
        descriptor.setShortDescription("Botones de votación con notificación de estado.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propVoto = new PropertyDescriptor("voto", BotonLikeDislike.class, "getVoto", "setVoto");
            propVoto.setDisplayName("Voto Actual");
            propVoto.setPreferred(true);
            return new PropertyDescriptor[]{propVoto};
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}
