package com.svalero.observerPattern;

public class Application {
    public static void main(String[] args) {
        Product product = new Product("Donuts", "Sin Azúcar", 10, 20); // Observable
        Customer customer = new Customer();
        
        product.addObserver(customer);
        product.decreasePrice(2);
        product.decreaseStock(5);
        product.decreasePrice(4);
    }
}