package Project.Service;

import Project.Booking.HouseBooking;
import Project.People.Client;
import Project.People.Owner;
import Project.Properties.House;

import java.util.*;

import static Project.Service.RentCar.clients;

public class RentHouse {
    private List <HouseBooking> houseBookings = new ArrayList<>();
    private TreeSet <Owner> owners = new TreeSet<>(new myComparator());
    private List <House> houses = new ArrayList<>();
    private List <House> allHouses = new ArrayList<>();
    public RentHouse () {}

    public List<HouseBooking> getHouseBookings() {
        return houseBookings;
    }

    public void setHouseBookings(List<HouseBooking> houseBookings) {
        this.houseBookings = houseBookings;
    }

    public TreeSet<Owner> getOwners() {
        return owners;
    }

    public void setOwners(TreeSet<Owner> owners) {
        this.owners = owners;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<House> getAllHouses() {
        return allHouses;
    }

    public void setAllHouses(List<House> allHouses) {
        this.allHouses = allHouses;
    }

    //    1. Show all available houses in a specific city
    public void citiesWithHouses () {
        List <String> cities = new ArrayList<>();
        for (var house : houses) {
            if (!cities.contains(house.getCity()))
                cities.add(house.getCity());
        }
        if (cities.isEmpty()) {
            System.out.println("There aren't any available houses");
        }
        else {
            System.out.print("There are available houses in the following cities: ");
            for (var city : cities)
                System.out.print(city + ", ");
        }
    }
    public boolean showHouses () {
        citiesWithHouses();
        boolean found = false;
        System.out.print("\nEnter the name of the city: ");
        Scanner r = new Scanner(System.in);
        String city = r.nextLine();
        StringBuilder result = new StringBuilder("These are the available houses in " + city + ":\n");
        for (var house : houses) {
            if (house.getCity().equals(city)) {
                found = true;
                result.append(house.toString() + '\n');
            }
        }
        if (found) {
            System.out.println(result);
            return true;
        }
        System.out.println("There aren't any available houses in " + city);
        return false;
    }
//    2. Rent a house
    public Client readClientName () {
        System.out.print("\nEnter the name of the client: ");
        String name;
        while (true) {
            Scanner r = new Scanner(System.in);
            name = r.nextLine();
            for (var client : clients)
                if (client.getName().equals(name)) {
                    return client;
                }
            System.out.println("There isn't any client with this name. Please enter another name: ");
        }
    }
    public boolean houseExists (int id) {
        for (var house : houses)
            if (house.getHouseId() == id)
                return true;
        System.out.println("There is no house with this id. Choose another id: ");
        return false;
    }
    public void rentHouse () {
        boolean ff = showClients();
        if (!ff)
            return;
        Client client = readClientName();
        HouseBooking houseBooking = new HouseBooking();
        houseBooking.setClient(client);

        boolean flag = showHouses();
        if (!flag)
            return;

        System.out.print("\nChoose the id of the house you want to rent: ");
        Scanner r = new Scanner(System.in);
        int id = Integer.parseInt(r.nextLine());

        while (!houseExists(id)) {
            r = new Scanner(System.in);
            id = Integer.parseInt(r.nextLine());
        }
        houseBooking.read();
        int totalPrice = 0;
        for (var house : houses) {
            if (house.getHouseId() == id) {
                totalPrice = house.getPrice() * houseBooking.getDuration();
                break;
            }
        }
        int balance = client.getBalance();
        if (totalPrice <= balance) {
            for (var house : houses) {
                if (house.getHouseId() == id) {
                    houseBooking.setHouse(house);
                    house.getOwner().addIncome(house.getOwner(), totalPrice);
                    houses.remove(house);
                    break;
                }
            }
            System.out.println("House rented successfully!");
            System.out.println("Total rental price: " + totalPrice + ". Old balance: " + balance + ". New balance: " + (balance - totalPrice));
            client.setBalance(balance - totalPrice);
            houseBookings.add(houseBooking);
        }
        else System.out.println("Not enough money!");
    }

//    5. Show all clients
    public boolean showClients () {
        if (clients.isEmpty()) {
            System.out.println("There aren't any clients");
            return false;
        }
        System.out.println("These are the clients:");
        for (var client : clients) {
            System.out.println(client.toString());
        }
        return true;
    }

//    3. Show all the houses booked by a client
    public void realEstate () {
        boolean flag = showClients();
        if (!flag)
            return;
        Client client = readClientName();
        boolean k = false;
        System.out.println(client.getName() + " rented the following houses: ");
        for (var houseBooking : houseBookings) {
            if (houseBooking.getClient().equals(client)) {
                System.out.println(houseBooking.getDuration() + " day booking - " + houseBooking.getHouse().toString());
                k = true;
            }
        }
        if (!k)
            System.out.println(client.getName() + " didn't rent any houses");
    }
//    4. Show all owners, sorted by their age, and their properties
    public void ownerProperties () {
        if (owners.isEmpty()) {
            System.out.println("There aren't any owners");
            return;
        }
        for (var owner : owners) {
            System.out.println("\n" + owner.getName() + ", aged " + owner.getAge() + ", owns " + owner.getNrProp() + " properties.");
            for (var house : allHouses) {
                if (house.getOwner().equals(owner))
                    System.out.println(house);
            }
        }
    }

//    6. Show all owners sorted by their age
//    public boolean showOwners () {
//        if (owners.isEmpty()) {
//            System.out.println("There aren't any owners");
//            return false;
//        }
//        System.out.println("These are the owners:");
//        for (var owner : owners) {
//            System.out.println(owner.toString());
//        }
//        return true;
//    }
    public void menuRentHouse() {
        int option = -1;
        while (option != 0) {
            System.out.println("\nChoose one of the following actions:");
            System.out.println("1. Show all available houses in a specific city");
            System.out.println("2. Rent a house");
            System.out.println("3. Show all the houses booked by a client");
            System.out.println("4. Show all owners and their properties (ordered by age)"); // ordered by age ascending
            System.out.println("5. Show all clients");
//            System.out.println("6. Show all owners sorted by their age");
//            System.out.println("2. Add a new house");
//            System.out.println("3. Remove a house");
            System.out.println("------------------------------");
            System.out.println("0. Back to menu");

            Scanner input = new Scanner(System.in);
            option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1: {
                    showHouses();
                    break;
                }
                case 2: {
                    rentHouse();
                    break;
                }
                case 3: {
                    realEstate();
                    break;
                }
                case 4: {
                    ownerProperties();
                    break;
                }
                case 5: {
                    showClients();
                    break;
                }
//                case 6: {
//                    showOwners();
//                    break;
//                }
                default: {
                    System.out.print("\nPlease enter a valid option: ");
                }
            }
        }
    }
}
