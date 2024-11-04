package org.example;

class Coffee {
    protected Intensity coffeeIntensity;
    protected final String name = "Coffee";

    public Coffee(Intensity coffeeIntensity) {
        this.coffeeIntensity = coffeeIntensity;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Intensity: " + coffeeIntensity);
    }
}
