package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
/**
 * Información de diseño (BeanInfo) del componente {@link BotonLikeDislike}.
 * <p>
 * Define el nombre y la descripción que muestra NetBeans en la paleta
 * y expone solo la propiedad {@code voto} en la ventana de propiedades.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see BotonLikeDislike
 * @see java.beans.SimpleBeanInfo
 */
public class BotonLikeDislikeBeanInfo extends SimpleBeanInfo {

    /**
    * Devuelve las propiedades que se muestran en la ventana Properties.
    * Solo se expone {@code voto} ("Voto Actual"), editable y marcada como preferida.
    *
    * @return un array con el descriptor de {@code voto}, o las propiedades
    *         por defecto si se produce un error de introspección
    */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(BotonLikeDislike.class);
        descriptor.setDisplayName("Botones Like/Dislike");
        descriptor.setShortDescription("Botones de votación con notificación de estado.");
        return descriptor;
    }

    /**
    * Devuelve las propiedades que se muestran en la ventana Properties.
    * Solo se expone {@code voto} ("Voto Actual"), editable y marcada como preferida.
    *
    * @return un array con el descriptor de {@code voto}, o las propiedades
    *         por defecto si se produce un error de introspección
    */
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
