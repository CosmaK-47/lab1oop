package org.example;

public interface Dineable {
    void serveDinner(String carId);
}

public interface Refuelable {
    void refuel(String carId);
}

public class ElectricStation implements Refuelable {
    @Override
    public void refuel(String carId) {
        System.out.println("Refueling electric car " + carId);
    }
}

public class PeopleDinner implements Dineable {
    @Override
    public void serveDinner(String carId) {
        System.out.println("Serving dinner to people in car " + carId);
    }
}

