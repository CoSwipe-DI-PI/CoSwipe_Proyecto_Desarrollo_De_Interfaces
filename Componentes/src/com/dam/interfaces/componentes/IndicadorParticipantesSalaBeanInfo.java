
package com.dam.interfaces.componentes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
/**
 * Información de diseño (BeanInfo) del componente
 * {@link IndicadorParticipantesSala}.
 * <p>
 * Expone en NetBeans solo la propiedad {@code numeroParticipantes},
 * con el nombre "Número de participantes".
 *
 * @author Sergio García de Baya
 * @version 2.0
 * @since 1.0
 * @see IndicadorParticipantesSala
 * @see java.beans.SimpleBeanInfo
 */

public class IndicadorParticipantesSalaBeanInfo extends SimpleBeanInfo {

    /**
    * Devuelve las propiedades que se muestran en la ventana Properties.
    * Solo se expone {@code numeroParticipantes} ("Número de participantes"),
    * con una descripción que aparece al pasar el ratón.
    *
    * @return un array con el descriptor de {@code numeroParticipantes},
    *         o {@code null} si se produce un error de introspección
    */
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
