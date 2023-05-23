package Bin;

import Project.Booking.Booking;
import Project.People.Client;

public class ApartmentBooking extends Booking {
    private Apartment apartment;

    public ApartmentBooking(Client client, int duration, Apartment apartment) {
        super(client, duration);
        this.apartment = apartment;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "ApartmentBooking{" +
                "apartment=" + apartment +
                ", client=" + client +
                ", duration=" + duration +
                '}';
    }
}
