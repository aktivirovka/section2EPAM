import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Run {
    public static void main(String[] args) {
        int parkingCapacity = 3;
        int numberOfCars = 6;
        int timeBetweenCars = 5;//млсек
        int waitForEmptySpace = 2;
        int parkingTime = 4;
        List<Car> allCars = new ArrayList<>();

        Parking parking = new Parking(parkingCapacity);
        for (int i = 1; i <= numberOfCars; i++) {
            Car car = new Car(parking, "Car_" + i, waitForEmptySpace, parkingTime);
            allCars.add(car);
            car.start();
            try {
                TimeUnit.MILLISECONDS.sleep(timeBetweenCars);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        waitForThreadsToFinish(allCars);
        System.out.println("List of cars that were parked");
        for (Car car : parking.getCarsParkedInParking()) {
            System.out.print(car.getName() + " ");
        }
    }

    public static void waitForThreadsToFinish(List<Car> allCars) {
        for (Car car : allCars) {
            while (car.getState().equals(Thread.State.NEW)) { //не стартовал поток
                try {
                    TimeUnit.MILLISECONDS.sleep(1);//main
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Car car : allCars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
