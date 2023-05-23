package Bin;

import java.util.Scanner;

public class App {

    private static App instance = null;

    private int appOption() {
        System.out.println("Choose one of the following actions:\n");
        System.out.println("1. Rent a car");
        System.out.println("2. Rent a house");
//        System.out.println("3. Rent an apartment");
//        System.out.println("4. Manage people");
        System.out.println("0. Exit\n");

        Scanner input = new Scanner(System.in);
        int option = Integer.parseInt(input.nextLine());

        while (option > 3 || option < 0) {
            System.out.print("\nPlease enter a valid option: ");
            option = Integer.parseInt(input.nextLine());
        }

        return option;
    }

    private void rentCarOption() {
        System.out.println("Choose one of the following actions:\n");

        System.out.println("1. Show all available cars from the dealership");
        System.out.println("2. Add a new car to the dealership");
        System.out.println("3. Remove a car from the dealership");
        System.out.println("4. Open garage (Show all the cars rented by a client)");
        System.out.println("5. Rent a car");
        //System.out.println("6. Cancel a car booking");

        System.out.println("------------------------------");

        System.out.println("0. Back to menu\n");

        Scanner input = new Scanner(System.in);
        int option = Integer.parseInt(input.nextLine());

        while (option > 8 || option < 0) {
            System.out.print("\nPlease enter a valid option: ");
            input = new Scanner(System.in);
            option = Integer.parseInt(input.nextLine());
        }

        switch (option) {
            case 0:
                menu();
                break;
            case 1:

            case 2:
            case 3:
            case 4:
            case 5:
        }

    }

//    private int serviceOption() {
//        System.out.println("Choose one of the following actions:\n");
//
//        System.out.println("1. Open my garage (Show all my cars)");
//
//        System.out.println("------------------------------");
//
//        System.out.println("2. Buy insurance");
//        System.out.println("3. Add an electric engine to a car");
//        System.out.println("4. Buy spoiler");
//        System.out.println("5. Buy new exhaust");
//
//        System.out.println("------------------------------");
//
//        System.out.println("6. Apply stage 1 to car");
//        System.out.println("7. Apply stage 2 to car");
//        System.out.println("8. Apply stage 3 to car");
//        System.out.println("9. Apply stage 4 to car");
//
//        System.out.println("------------------------------");
//
//        System.out.println("0. Back to menu\n");
//
//        Scanner input = new Scanner(System.in);
//        int option = Integer.parseInt(input.nextLine());
//
//        while (option > 9 || option < 0) {
//            System.out.print("\nPlease enter a valid option: ");
//            option = Integer.parseInt(input.nextLine());
//        }
//
//        return option;
//    }
//
//    private int realEstateOption() {
//        System.out.println("Choose one of the following actions:\n");
//
//        System.out.println("1. Open my garage (Show all my cars)");
//
//        System.out.println("------------------------------");
//
//        System.out.println("2. Buy insurance");
//        System.out.println("3. Add an electric engine to a car");
//        System.out.println("4. Buy spoiler");
//        System.out.println("5. Buy new exhaust");
//
//        System.out.println("------------------------------");
//
//        System.out.println("6. Apply stage 1 to car");
//        System.out.println("7. Apply stage 2 to car");
//        System.out.println("8. Apply stage 3 to car");
//        System.out.println("9. Apply stage 4 to car");
//
//        System.out.println("------------------------------");
//
//        System.out.println("0. Back to menu\n");
//
//        Scanner input = new Scanner(System.in);
//        int option = Integer.parseInt(input.nextLine());
//
//        while (option > 9 || option < 0) {
//            System.out.print("\nPlease enter a valid option: ");
//            option = Integer.parseInt(input.nextLine());
//        }
//
//        return option;
//    }


    private void menu() {

        while (true) {

            int choice = appOption();

            switch (choice) {
                case 0 -> {
                    System.out.println("Goodbye, have a nice day!");
                    System.exit(0);
                }
                case 1 -> {
                    rentCarOption();
                }
//                case 2 -> {
//                    while (true) {
//                        int op2 = serviceOption();
//                        switch (op2) {
//                            case 0:
//                                menu(rentCar, service);
//                                break;
//                            case 1:
//                                System.out.println(1);
//                                break;
//                            case 2:
//                                System.out.println(2);
//                                break;
//                            case 3:
//                                System.out.println(3);
//                                break;
//                        }
//                    }
//
//                }
            }
        }
    }
//    private void rentCarMenu() {
//        while (true) {
//            int op1 = rentCarOption();
//            switch (op1) {
//                case 0:
//                    menu();
//                    break;
//                case 1:
//                    System.out.println(1);
//                    break;
//                case 2:
//                    System.out.println(2);
//                    break;
//                case 3:
//                    System.out.println(3);
//                    break;
//            }
//        }
//    }


//    public static synchronized App getInstance() {
//        if(instance == null)
//            instance = new App();
//        return instance;
//    }
//
//    public void start() {
//        RentCar rentCar = rentCar.getInstance();
//        Project.Service service = Project.Service.getInstance();
//        this.menu(rentCar, service);
//    }
}
