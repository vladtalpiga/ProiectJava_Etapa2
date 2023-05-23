package Bin;

import Project.People.Owner;
import Project.Properties.House;

public class Apartment extends House {

    private static int countApId = 300;
    private int apId;
    private int floor;

    public Apartment(int price, Owner owner, int area, int nrPers, String city, int floor) {
        super(price, owner, area, nrPers, city);
        this.apId = ++countApId;
        this.floor = floor;
    }
    public Apartment () {
        super();
        this.apId = ++countApId;
    }
    public Apartment(int floor) {
        this.apId = ++countApId;
        this.floor = floor;
    }
    public int getApId() {
        return apId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apId=" + apId +
                ", price=" + price +
                ", owner=" + owner +
                ", area=" + area +
                ", nrPers=" + nrPers +
                ", city='" + city + '\'' +
                ", floor=" + floor +
                '}';
    }
}
