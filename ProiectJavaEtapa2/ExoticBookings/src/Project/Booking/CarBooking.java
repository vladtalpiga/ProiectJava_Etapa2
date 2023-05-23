package Project.Booking;

import Project.People.Client;
import Project.Properties.Car;

public class CarBooking extends Booking {
    private Car car;
//    private String dealership;

    public CarBooking(Client client, int duration, Car car) {
        super(client, duration);
        this.car = car;
    }

    public CarBooking () {
        super();
    }

//    public CarBooking(Client client, int duration, Car car, String dealership) {
//        super(client, duration);
//        this.car = car;
//        this.dealership = dealership;
//    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

//    public String getDealership() {
//        return dealership;
//    }
//
//    public void setDealership(String dealership) {
//        this.dealership = dealership;
//    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "car=" + car +
//                ", dealership='" + dealership + '\'' +
                ", client=" + client +
                ", duration=" + duration +
                '}';
    }
}
