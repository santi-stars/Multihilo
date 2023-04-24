package com.svalero.observerPattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Clase Customer
 * En este caso es la clase que observa (Observer)
 * Observa los cambios que se produzcan en otra clase, a la que se conoce como
 * Observable
 */
public class Customer implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("price")) {
            float newPrice = (float) event.getNewValue();
            float oldPrice = (float) event.getOldValue();
            System.out.println("El precio ha bajado " + (oldPrice - newPrice) + " €");
            if (newPrice < 7) {
                System.out.println("Comprar");
            } else {
                System.out.println("Todavía es muy caro. No comprar");
            }
        } else {
            System.out.println("No me interesa este evento");
        }

    }

}