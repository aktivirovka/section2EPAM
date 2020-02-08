import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunnerThreads {
    static int parkingCapacity = 3;

    public static void main(String[] args) throws InterruptedException {

        int numberOfCars = 10;
        int timeBetweenCars = 100;
        int waitForEmptySpace = 4000;
        int parkingTime = 3;

        Parking parking = new Parking(parkingCapacity);

        for (int i = 0; i < numberOfCars; i++) {
            Car car = new Car(parking, "Car_" + i, waitForEmptySpace, parkingTime);
            TimeUnit.MILLISECONDS.sleep(timeBetweenCars);
        }

        goFromStation(Car.listCars);
        for (Car car:parking.getCarsVisitedParking()) {
            System.out.println(car.getName());
        }

    }

    public static void goFromStation(List<Thread> allCars) {
        for (Thread thread : allCars) {
            try {
                thread.join();
                System.out.println(thread.getName() + " покинул парковку");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
