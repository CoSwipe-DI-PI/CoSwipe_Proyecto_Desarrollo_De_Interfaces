
package com.dam.interfaces.componentes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

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
