import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create stations
        Queue<Car> queue1 = new ArrayQueue<>();
        Queue<Car> queue2 = new ArrayQueue<>();

        CarStation electricPeopleStation = new CarStation(
                queue1, new PeopleDinner(), new ElectricStation(), "ELECTRIC", "PEOPLE");
        CarStation gasRobotStation = new CarStation(
                queue2, new RobotDinner(), new GasStation(), "GAS", "ROBOTS");

        List<CarStation> stations = new ArrayList<>();
        stations.add(electricPeopleStation);
        stations.add(gasRobotStation);

        // Create Semaphore
        Semaphore semaphore = new Semaphore(stations);

        // Simulate cars
        Car car1 = new Car("1", "ELECTRIC", "PEOPLE", true);
        Car car2 = new Car("2", "GAS", "ROBOTS", false);

        // Guide cars
        semaphore.guideCar(car1);
        semaphore.guideCar(car2);

        // Serve cars
        for (CarStation station : stations) {
            station.serveCars();
        }
    }
}
