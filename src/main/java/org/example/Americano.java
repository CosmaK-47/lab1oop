package org.example;

public class Americano extends Coffee {
    private int mlOfWater;

    public Americano(Intensity coffeeIntensity, int mlOfWater) {
        super(coffeeIntensity);
        this.mlOfWater = mlOfWater;
    }

    public int getMlOfWater() {
        return mlOfWater;
    }

    public void setMlOfWater(int mlOfWater) {
        this.mlOfWater = mlOfWater;
    }

    public Americano makeAmericano() {
        System.out.println("Making an Americano...");
        System.out.println("Adding " + mlOfWater + " ml of water.");
        return this;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Water: " + mlOfWater + " ml");
    }
}
