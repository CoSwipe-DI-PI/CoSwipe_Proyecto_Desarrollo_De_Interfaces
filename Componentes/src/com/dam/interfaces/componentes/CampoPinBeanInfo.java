package com.dam.interfaces.componentes;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class CampoPinBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(CampoPin.class);
        descriptor.setDisplayName("Campo PIN 6 Dígitos");
        descriptor.setShortDescription("Valida un PIN de 6 números.");
        return descriptor;
    }

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
