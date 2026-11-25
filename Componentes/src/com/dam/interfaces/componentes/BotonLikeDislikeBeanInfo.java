package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;


/**
 * Información de diseño (JavaBeans) del componente {@code BotonLikeDislike}.
 *
 * <p>Indica al editor visual de NetBeans qué propiedades, eventos y
 * icono muestra el componente en la paleta y en la ventana de
 * propiedades. Esta clase se genera con el editor de BeanInfo del IDE y
 * no debe modificarse manualmente.</p>
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @see java.beans.SimpleBeanInfo
 */
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
