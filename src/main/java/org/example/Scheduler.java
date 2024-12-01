package org.example;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void main(String[] args) {
        // Create CarStations
        Queue<Car> electricQueue = new ArrayQueue<>();
        Queue<Car> gasQueue = new ArrayQueue<>();
        CarStation electricStation = new CarStation(electricQueue, new PeopleDinner(),
                new ElectricStation(), "ELECTRIC", "PEOPLE");
        CarStation gasStation = new CarStation(gasQueue, new RobotDinner(),
                new GasStation(), "GAS", "ROBOTS");

        Semaphore semaphore = new Semaphore(List.of(electricStation, gasStation));

        // Setup File Watcher
        FileWatcher fileWatcher = new FileWatcher("queue");

        // Create Scheduler
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Task 1: Fetch cars and assign them to stations
        scheduler.scheduleAtFixedRate(() -> {
            List<Car> cars = fileWatcher.fetchCars();
            for (Car car : cars) {
                semaphore.guideCar(car);
            }
        }, 0, 2, TimeUnit.SECONDS);

        // Task 2: Serve cars from stations
        scheduler.scheduleAtFixedRate(() -> {
            electricStation.serveCars();
            gasStation.serveCars();
        }, 0, 5, TimeUnit.SECONDS);
    }
}
