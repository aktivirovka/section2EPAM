import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private int parkingCapacity;
    int occupiedSpaces = 0;
    private List<Car> carsParkedInParking = new ArrayList<>();

    public List<Car> getCarsParkedInParking() {
        return carsParkedInParking;
    }

    public Parking(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public boolean tryAccessToPark(Car car) {
        try {
            lock.lock();

            while (occupiedSpaces == parkingCapacity) {
                condition.await(car.getWaitForEmptySpace(), TimeUnit.SECONDS);
                if (occupiedSpaces == parkingCapacity) {
                    return false;
                } else break;
            }
            occupiedSpaces++;
            carsParkedInParking.add(car);
            System.out.println(car.getName() + " is taking the parking space");
            System.out.println("Number of occupied parking spaces is " + occupiedSpaces);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }

    public void makeSpace(Car car) {
        try {
            lock.lock();
            if (carsParkedInParking.contains(car)) {
                occupiedSpaces--;
                System.out.println(car.getName() + " has made space");
                System.out.println(car.getName() + " has left the parking");
                System.out.println("Number of occupied parking spaces - " + occupiedSpaces);
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
