package CSC375HW2JDK;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Passenger implements Runnable {
    private int passengerNumber;
    private String flight;
    private Random random;
    private ConcurrentHashMap<String, FlightDetails> concurrentHashMap;


    Passenger(int passengerNumber, String flight, ConcurrentHashMap<String, FlightDetails> concurrentHashMap) {
        this.passengerNumber = passengerNumber;
        this.flight = flight;
        this.concurrentHashMap = concurrentHashMap;
        random = new Random();
    }

    private void readFlightDetails() {
        FlightDetails f = concurrentHashMap.get(flight);
        System.out.printf("%-20s %-20s %-20s\n", "PASSENGER: " + passengerNumber, f.getFlightIdentification(), f.getFlightStatus());
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                System.out.println("Passenger Interrupted. Shutting down.");
                return;
            }
            readFlightDetails();
        }
    }
}
