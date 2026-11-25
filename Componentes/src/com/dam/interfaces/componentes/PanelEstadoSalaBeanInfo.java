package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
/**
 * Información de diseño (BeanInfo) del componente {@link PanelEstadoSala}.
 * <p>
 * Define el nombre ("Estado de Sala") y la descripción que muestra NetBeans
 * en la paleta, y expone la propiedad {@code conectado} como editable.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see PanelEstadoSala
 * @see java.beans.SimpleBeanInfo
 */
public class PanelEstadoSalaBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(PanelEstadoSala.class);
        descriptor.setDisplayName("Estado de Sala");
        descriptor.setShortDescription("Indicador visual de conexión.");
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propConectado = new PropertyDescriptor("conectado", PanelEstadoSala.class, "isConectado", "setConectado");
            propConectado.setDisplayName("Conectado");
            propConectado.setPreferred(true);
            return new PropertyDescriptor[]{propConectado};
        } catch (IntrospectionException e) {
            return super.getPropertyDescriptors();
        }
    }
}
