package org.example;

public class PumpkinSpiceLatte extends Coffee {
    private int mlOfMilk;
    private int mgOfPumpkinSpice;

    public PumpkinSpiceLatte(Intensity coffeeIntensity, int mlOfMilk, int mgOfPumpkinSpice) {
        super(coffeeIntensity); // Pass Intensity to Coffee constructor
        this.mlOfMilk = mlOfMilk;
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    public void makePumpkinSpiceLatte() {
        System.out.println("Making a Pumpkin Spice Latte...");
        System.out.println("Adding " + mlOfMilk + " mL of milk.");
        System.out.println("Adding " + mgOfPumpkinSpice + " mg of pumpkin spice.");
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Type: Pumpkin Spice Latte");
        System.out.println("Milk: " + mlOfMilk + " mL");
        System.out.println("Pumpkin Spice: " + mgOfPumpkinSpice + " mg");
    }
}
