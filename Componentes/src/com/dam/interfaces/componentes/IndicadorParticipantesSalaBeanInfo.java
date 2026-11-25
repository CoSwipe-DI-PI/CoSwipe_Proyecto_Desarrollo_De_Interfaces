
package com.dam.interfaces.componentes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
/**
 * Información de diseño (BeanInfo) del componente
 * {@link IndicadorParticipantesSala}.
 * <p>
 * Expone en el editor de propiedades de NetBeans solo la propiedad
 * {@code numeroParticipantes}, con nombre y descripción en español.
 *
 * @author Sergio García de Baya
 * @version 1.0
 * @since 1.0
 * @see IndicadorParticipantesSala
 * @see java.beans.SimpleBeanInfo
 */
public class IndicadorParticipantesSalaBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor numeroParticipantes =
                    new PropertyDescriptor(
                            "numeroParticipantes",
                            IndicadorParticipantesSala.class
                    );

            numeroParticipantes.setDisplayName("Número de participantes");
            numeroParticipantes.setShortDescription(
                    "Indica cuántas personas hay en la sala"
            );

            return new PropertyDescriptor[]{
                numeroParticipantes
            };

        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
