package Project;

import Project.Service.HousesOwners;
import Project.Service.RentCar;
import java.util.*;

public class Main {
    static void setup () {
        RentCar rentCar = new RentCar();
        HousesOwners housesOwners = new HousesOwners();

        while (true) {
            System.out.println("\nChoose one of the following actions:");
            System.out.println("1. Car rental");
            System.out.println("2. House rental");
            System.out.println("0. Exit");

            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 0: {
                    System.exit(0);
                }
                case 1: {
                    rentCar.menuRentCar();
                    break;
                }
                case 2: {
                    housesOwners.menuHouseOwners();
                    break;
                }
                default: {
                    System.out.println("\nPlease enter a valid option!");
                }
            }
        }
    }
    public static void main(String[] args) {

        setup();

    }
}