package org.example;

public class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;

    public SyrupCappuccino(Intensity coffeeIntensity, int mlOfMilk, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.syrup = syrup;
    }

    public SyrupType getSyrup() {
        return syrup;
    }

    public void setSyrup(SyrupType syrup) {
        this.syrup = syrup;
    }

    public SyrupCappuccino makeSyrupCappuccino() {
        System.out.println("Making a Syrup Cappuccino...");
        System.out.println("Adding syrup: " + syrup);
        return this;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Syrup: " + syrup);
    }
}
