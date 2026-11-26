package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Información de diseño (BeanInfo) del componente {@link CampoPin}.
 * <p>
 * Define el nombre y la descripción que muestra NetBeans en la paleta
 * y expone la propiedad {@code pinCompleto} como solo lectura.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see CampoPin
 * @see java.beans.SimpleBeanInfo
 */
public class CampoPinBeanInfo extends SimpleBeanInfo {

    /**
    * Devuelve el descriptor del componente con el nombre que se ve en la
    * paleta ("Campo PIN 6 Dígitos") y su descripción corta.
    *
    * @return el {@link BeanDescriptor} de {@link CampoPin}
    */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(CampoPin.class);
        descriptor.setDisplayName("Campo PIN 6 Dígitos");
        descriptor.setShortDescription("Valida un PIN de 6 números.");
        return descriptor;
    }

    /**
    * Devuelve las propiedades que se muestran en la ventana Properties.
    * Solo se expone {@code pinCompleto} ("PIN Completo"), de solo lectura
    * porque no tiene setter, y marcada como preferida.
    *
    * @return un array con el descriptor de {@code pinCompleto}, o las propiedades
    *         por defecto si se produce un error de introspección
    */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propPin = new PropertyDescriptor("pinCompleto", CampoPin.class, "getPinCompleto", null);
            propPin.setDisplayName("PIN Completo");
            propPin.setPreferred(true);
            return new PropertyDescriptor[]{propPin};
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}
