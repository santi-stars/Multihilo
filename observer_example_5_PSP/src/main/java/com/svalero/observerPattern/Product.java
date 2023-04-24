package com.svalero.observerPattern;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Clase Product
 * Es la clase Observable. Los cambios que se produzcan serán
 * observados inmediatamente por las clases Observer que se registren
 * desde ésta
 */
public class Product {

    private String name;
    private String description;
    private float price;
    private int stock;

    private PropertyChangeSupport support;

    public Product(String name, String description, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.support = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer) {
        this.support.addPropertyChangeListener(observer);
    }

    public void removeObserver(PropertyChangeListener observer) {
        this.support.removePropertyChangeListener(observer);
    }

    public void decreasePrice(float value) {
        this.support.firePropertyChange("price", this.price, (this.price - value));
        this.price -= value;
    }

    public void decreaseStock(int amount) {
        this.support.firePropertyChange("stock", this.stock, (this.stock - amount));
        this.stock -= amount;
    }

}