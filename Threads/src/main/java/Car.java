import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private Thread carThread;
    private Parking parking;
    static List<Thread> listCars = new ArrayList<>();
    String name;
    int waitForEmptySpace;
    int parkingTime;

    public int getWaitForEmptySpace() {
        return waitForEmptySpace;
    }

    public Car(Parking parking, String name, int waitForEmptySpace, int parkingTime) {
        this.parking = parking;
        this.waitForEmptySpace = waitForEmptySpace;
        this.parkingTime = parkingTime;
        carThread = new Thread(this, name);
        listCars.add(carThread);
        carThread.start();
    }

    @Override
    public void run() {
        System.out.println(carThread.getName() + " приехал на стоянку");
        try {
            if (parking.tryAccessToPark(this)) {
                System.out.println(carThread.getName() + " стоит на стоянке");
                TimeUnit.SECONDS.sleep(parkingTime);
                parking.makeSpace(this);
            }else System.out.println(carThread.getName() + " время ожидания вышло");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }
}

