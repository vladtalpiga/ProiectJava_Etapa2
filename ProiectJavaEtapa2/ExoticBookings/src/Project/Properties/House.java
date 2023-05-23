package Project.Properties;

import Project.People.Owner;

import java.util.Scanner;

public class House {
    private static int countHouseId = 200;
    private int houseId;
    protected int price; // price per night
    protected Owner owner;
    protected int area; // in square metres
    protected int nrPers; // number of persons suitable for living in property
    protected String city; // address city
    protected int ownerId;

    public House(int price, Owner owner, int area, int nrPers, String city) {
        this.houseId = ++countHouseId;
        this.price = price;
        owner.incrementNrProp(owner);
        this.owner = owner;
        this.area = area;
        this.nrPers = nrPers;
        this.city = city;
        this.ownerId = ownerId;
    }

    public void read () {
        Scanner r = new Scanner(System.in);

        System.out.print("Owner id: ");
        this.ownerId = Integer.parseInt(r.nextLine());

        System.out.print("Price per night: ");
        this.price = Integer.parseInt(r.nextLine());

        System.out.print("Location: ");
        this.city = r.nextLine();
    }
    public void readWithoutOwner () {
        Scanner r = new Scanner(System.in);

        System.out.print("Price per night: ");
        this.price = Integer.parseInt(r.nextLine());

        System.out.print("Location: ");
        this.city = r.nextLine();
    }
    public House () {
        this.houseId = ++countHouseId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getHouseId() {
        return houseId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public int getNrPers() {
        return nrPers;
    }
    public void setNrPers(int nrPers) {
        this.nrPers = nrPers;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "House " + houseId + " in " + city + ", costs " + price + " per night, with an area of " + area + ", can accommodate " + nrPers + " pers. House owned by: " + owner;
    }
}
