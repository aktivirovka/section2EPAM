import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Parking {
    private int parkingCapacity;
    int carLimit = 0;

    private List<Car> carsWereOnParking = new ArrayList<>();
    private List<Car> carsVisitedParking = new ArrayList<>();

    public List<Car> getCarsWereOnParking() {
        return carsWereOnParking;
    }

    public List<Car> getCarsVisitedParking() {
        return carsVisitedParking;
    }

    public Parking(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }


    public synchronized boolean tryAccessToPark(Car car) throws InterruptedException {
        while (carLimit == parkingCapacity) {
           // TimeUnit.SECONDS.wait(car.getWaitForEmptySpace());
            wait(car.getWaitForEmptySpace());
            if (carLimit == parkingCapacity) return false;
            else break;
        }
        carLimit++;
        carsWereOnParking.add(car);
        notifyAll();
        return true;
    }

    public synchronized void makeSpace(Car car) {
        if (carsWereOnParking.contains(car)) {
            carsVisitedParking.add(car);
            carLimit--;
            carsWereOnParking.remove(car);
        }
    }
}
