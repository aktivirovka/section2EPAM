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
        List<Car> listCars = new ArrayList<>();

        for (int i = 0; i < numberOfCars; i++) {
            Car car = new Car(parking, "Car_" + i, waitForEmptySpace, parkingTime);
            listCars.add(car);
            car.start();
            TimeUnit.MILLISECONDS.sleep(timeBetweenCars);
        }

        goFromStation(listCars);
        System.out.println("список по стоянке");
        for (Car car:parking.getCarsVisitedParking()) {
            System.out.println(car.getName());
        }

    }

    public static void goFromStation(List<Car> allCars) {
        for (Car car : allCars) {
            try {
                car.join();
                System.out.println(car.getName() + " покинул парковку");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
