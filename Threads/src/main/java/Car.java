import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Car extends Thread {
    private Parking parking;

    int waitForEmptySpace;
    int parkingTime;

    public int getWaitForEmptySpace() {
        return waitForEmptySpace;
    }

    public Car(Parking parking, String name, int waitForEmptySpace, int parkingTime) {
        super(name);
        this.parking = parking;
        this.waitForEmptySpace = waitForEmptySpace;
        this.parkingTime = parkingTime;
    }

    @Override
    public void run() {
        System.out.println(getName() + " приехал на стоянку");
        try {
            if (parking.tryAccessToPark(this)) {
                System.out.println(getName() + " стоит на стоянке");
                TimeUnit.SECONDS.sleep(parkingTime);
                parking.makeSpace(this);
            }else System.out.println(getName() + " время ожидания вышло");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

