package Project.Booking;

import Project.People.Client;
import Project.Properties.House;

public class HouseBooking extends Booking {
    private House house;
    public HouseBooking(Client client, int duration, House house) {
        super(client, duration);
        this.house = house;
    }
    public HouseBooking () {
        super();
    }
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "HouseBooking{" +
                "house=" + house +
                ", client=" + client +
                ", duration=" + duration +
                '}';
    }
}
