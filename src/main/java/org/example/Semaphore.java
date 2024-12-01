import java.util.List;

public class Semaphore {
    private List<CarStation> stations;

    public Semaphore(List<CarStation> stations) {
        this.stations = stations;
    }

    public void guideCar(Car car) {
        for (CarStation station : stations) {
            if (station.canServe(car)) {
                station.addCar(car);
                return;
            }
        }
        System.out.println("No station available for car: " + car.getId());
    }
}
