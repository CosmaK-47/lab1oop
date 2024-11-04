package org.example;



public class Cappuccino extends Coffee {
    private int mlOfMilk;

    public Cappuccino(Intensity coffeeIntensity, int mlOfMilk) {
        super(coffeeIntensity);
        this.mlOfMilk = mlOfMilk;
    }

    public int getMlOfMilk() {
        return mlOfMilk;
    }

    public void setMlOfMilk(int mlOfMilk) {
        this.mlOfMilk = mlOfMilk;
    }

    public Cappuccino makeCappuccino() {
        System.out.println("Making a Cappuccino...");
        System.out.println("Adding " + mlOfMilk + " ml of milk.");
        return this;
    }
}
