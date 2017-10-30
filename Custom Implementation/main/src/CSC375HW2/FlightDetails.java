package CSC375HW2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that simulates a Flight and its details.
 */
class FlightDetails {
    private String flightIdentification;
    private FlightStatus flightStatus;
    private AtomicInteger flightHappiness;
    private FlightDetails next;

    /**
     *
     * @param flightIdentification identification
     * @param flightStatus status of the flight
     */
    FlightDetails(String flightIdentification, FlightStatus flightStatus) {
        this.flightIdentification = flightIdentification;
        this.flightStatus = flightStatus;
        flightHappiness = new AtomicInteger(100);
    }

    /**
     *
     * @param flightStatus updated flight status
     * @return true if the flight status can be updated or false because the flight already has that status currently
     */
    boolean setFlightStatus(FlightStatus flightStatus) {
        if(this.flightStatus.equals(flightStatus)){
            return false;
        }
        this.flightStatus = flightStatus;
        setFlightHappiness(flightStatus);
        return true;
    }

    /**
     *
     * @return current flight happiness level
     */
    int getHappiness(){
        return flightHappiness.get();
    }

    /**
     *
     * @param status updated flight status
     */
    private void setFlightHappiness(FlightStatus status){
        flightHappiness.compareAndSet(flightHappiness.get(), flightHappiness.get() + happinessAmount(status));
    }

    /**
     *
     * @param flightStatus updated flight status
     * @return integer based on the flightStatus parameter
     */
    private int happinessAmount(FlightStatus flightStatus){
        switch (flightStatus){
            case ON_TIME:
                return 1;
            case DELAYED:
                return -1;
            case CANCELLED:
                return -5;
            case NO_DATA_CURRENTLY:
                return -3;
        }
        return 0;
    }

    /**
     *
     * @return flight identification
     */
    String getFlightIdentification() {
        return flightIdentification;
    }

    /**
     *
     * @return flight status
     */
    FlightStatus getFlightStatus() {
        return flightStatus;
    }

    /**
     *
     * @return next flight hashed to the same index in the linked list
     */
    FlightDetails getNext() {
        return next;
    }

    /**
     *
     * @param next flight to be added to the linked list
     */
    void setNext(FlightDetails next) {
        this.next = next;
    }
}