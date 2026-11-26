package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
/**
 * Información de diseño (BeanInfo) del componente {@link PanelEstadoSala}.
 * <p>
 * Define el nombre y la descripción que muestra NetBeans en la paleta
 * y expone solo la propiedad {@code conectado}.
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see PanelEstadoSala
 * @see java.beans.SimpleBeanInfo
 */
public class PanelEstadoSalaBeanInfo extends SimpleBeanInfo {

    /**
    * Devuelve el descriptor del componente con el nombre que se ve en la
    * paleta ("Estado de Sala") y su descripción corta.
    *
    * @return el {@link BeanDescriptor} de {@link PanelEstadoSala}
    */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(PanelEstadoSala.class);
        descriptor.setDisplayName("Estado de Sala");
        descriptor.setShortDescription("Indicador visual de conexión.");
        return descriptor;
    }

    /**
    * Devuelve las propiedades que se muestran en la ventana Properties.
    * Solo se expone {@code conectado} ("Conectado"), editable y marcada como preferida.
    *
    * @return un array con el descriptor de {@code conectado}, o las propiedades
    *         por defecto si se produce un error de introspección
    */
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
