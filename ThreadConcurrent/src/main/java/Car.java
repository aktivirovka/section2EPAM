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
        System.out.println(getName() + " has come to the parking");
        try {
            if (parking.tryAccessToPark(this)) {
                TimeUnit.SECONDS.sleep(parkingTime);
                parking.makeSpace(this);
            } else {
                System.out.println(getName() + " - the waiting time IS OVER");
                System.out.println(getName() + " has left the parking");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
