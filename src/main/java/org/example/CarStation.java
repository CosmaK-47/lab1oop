package org.example;

public class CarStation {
    private Queue<Car> queue;
    private Dineable diningService;
    private Refuelable refuelingService;
    private String carType; // "ELECTRIC" or "GAS"
    private String passengerType; // "PEOPLE" or "ROBOTS"

    public CarStation(Queue<Car> queue, Dineable diningService, Refuelable refuelingService,
                      String carType, String passengerType) {
        this.queue = queue;
        this.diningService = diningService;
        this.refuelingService = refuelingService;
        this.carType = carType;
        this.passengerType = passengerType;
    }

    public boolean canServe(Car car) {
        return car.getType().equals(carType) && car.getPassengers().equals(passengerType);
    }

    public void addCar(Car car) {
        queue.enqueue(car);
    }

    public void serveCars() {
        while (!queue.isEmpty()) {
            Car car = queue.dequeue();
            if (car.isDining()) diningService.serveDinner(car.getId());
            refuelingService.refuel(car.getId());
        }
    }
}

