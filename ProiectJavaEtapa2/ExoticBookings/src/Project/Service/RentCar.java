package Project.Service;

import Project.Booking.CarBooking;
import Project.Database.Audit;
import Project.Database.JDBC;
import Project.People.Client;
import Project.Properties.Car;

import java.sql.*;
import java.util.*;

public class RentCar {

//    private static RentCar instance = null;
    private List<Car> cars = new ArrayList<>();
    private List<CarBooking> carBookings = new ArrayList<>();
    protected static List<Client> clients = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarBooking> getCarBookings() {
        return carBookings;
    }

    public void setCarBookings(List<CarBooking> carBookings) {
        this.carBookings = carBookings;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public RentCar(){}
//    public static synchronized RentCar getInstance() {
//        if (instance == null)
//            instance = new RentCar();
//        return instance;
//    }

    // 1. Show all cars from the dealership

    public boolean showCars() {
        String sql = "select * from car";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                k = true;
                //I have at least one record in the result set
                System.out.print("Car " + resultSet.getInt(1) + ". " + resultSet.getInt(4) + " " + resultSet.getString(3) + ", Price / Day = " + resultSet.getInt(2));
                if (resultSet.getInt(5) == 0) {
                    System.out.println(" - Available");
                } else {
                    System.out.println(" - Rented");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There are no available cars");
        return k;
    }

//    public boolean showCars() {
//        if(cars.isEmpty()) {
//            System.out.println("There are no available cars");
//            return false;
//        }
//            System.out.println("\nThese are the available cars:");
//            for (var car : cars) {
//                System.out.println(car.toString());
//            }
//        return true;
//    }

    // 2. Search car by id
    public boolean carExists (int id) {
        String sql = "select * from car where idCar = ?";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                k = true;
//                System.out.println("There is an available car with id = " + id + ": ");
                System.out.print("Car " + resultSet.getInt(1) + ". " + resultSet.getInt(4) + " " + resultSet.getString(3) + ", Price / Day = " + resultSet.getInt(2));
                if (resultSet.getInt(5) == 0) {
                    System.out.println(" - Available");
                } else {
                    System.out.println(" - Rented");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There is no available car with id = " + id + "\n");
        return k;
    }

//    public boolean carExists (int id) {
//        for (var car : cars)
//            if (car.getIdCar() == id)
//                return true;
//        System.out.println("There is no car with this id. Choose another id: ");
//        return false;
//    }


    // 3. Add a new car to the dealership

    public void addCar() {
        String sql = "insert into car values (null, ?, ?, ?, 0) ";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            System.out.println("Add a new car: ");
            Car car = new Car();
            car.read();
//            cars.add(car);
//            statement.setInt(1, car.getIdCar());
            statement.setInt(1, car.getcarPrice());
            statement.setString(2, car.getBrand());
            statement.setInt(3, car.getYear());
            statement.executeUpdate();
            System.out.println("Car added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(int id) {
        String sql = "insert into car values (?, ?, ?, ?, 0) ";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            System.out.println("Add a new car: ");
            Car car = new Car();
            car.read();
            System.out.println("Car added successfully");
//            cars.add(car);
//            statement.setInt(1, car.getIdCar());
            statement.setInt(1, id);
            statement.setInt(2, car.getcarPrice());
            statement.setString(3, car.getBrand());
            statement.setInt(4, car.getYear());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    public void addCar() {
//        System.out.println("Add a new car: ");
//        Car car = new Car();
//        car.read();
//        cars.add(car);
//        System.out.println("Car added successfully");
//    }

    //    4. Edit a car from the dealership
    public void editCar() {
        showCars();
        System.out.print("Enter the id of the car you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (carExists(id)) {
            String sql = "update car set carPrice = ?, brand = ?, year = ? where idCar = ?";
            try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                System.out.println("Enter the new info: ");
                Car car = new Car();
                car.read();
//            cars.add(car);
//            statement.setInt(1, car.getIdCar());
                statement.setInt(1, car.getcarPrice());
                statement.setString(2, car.getBrand());
                statement.setInt(3, car.getYear());
                statement.setInt(4, id);
                System.out.println("Car edited successfully");
                statement.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 5. Remove a car from the dealership
    public void removeCar() {
        if (showCars()) {
            System.out.print("\nChoose the id of the car you want to remove: ");

            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());

            if (carExists(id)) {
                String sql = "delete from car where idCar = ?";
                try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    System.out.println("Car removed successfully");
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("There is no available car with id = " + id + "\n");
            }
        }
    }
//    public void removeCar() {
//        boolean flag = showCars();
//
//        if (flag) {
//            System.out.println("\nChoose the id of the car you want to remove: ");
//
//            Scanner r = new Scanner(System.in);
//            int id = Integer.parseInt(r.nextLine());
//
//            while (!carExists(id)) {
//                r = new Scanner(System.in);
//                id = Integer.parseInt(r.nextLine());
//            }
//            int finalId = id;
//            cars.removeIf(car -> car.getIdCar() == finalId);
//        }
//
////        for (var car : cars) {
////            if (car.getIdCar() == id)
////                cars.remove(car);
////        }
//
//        System.out.println("Car removed successfully");
//    }


    // 6. Show all clients
    public boolean showClients() {
        String sql = "select * from client";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                k = true;
                //I have at least one record in the result set
                System.out.println("Client " + resultSet.getInt(1) + ". " + resultSet.getString(2) + ", from " + resultSet.getString(4) + ", age: " + resultSet.getInt(3) + ", balance: " + resultSet.getInt(5));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There aren't any clients");
        return k;
    }
//    7. Search client by id
    public boolean clientExists (int id) {
        String sql = "select * from client where idClient = ?";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                k = true;
//                System.out.println("There is a client with the id = " + id + ": ");
                System.out.println("Client " + resultSet.getInt(1) + ". " + resultSet.getString(2) + ", from " + resultSet.getString(4) + ", age: " + resultSet.getInt(3) + ", balance: " + resultSet.getInt(5));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There isn't any client with the id = " + id + "\n");
        return k;
    }

//    public boolean showClients () {
//        if (clients.isEmpty()) {
//            System.out.println("There aren't any clients");
//            return false;
//        }
//        System.out.println("These are the clients:");
//        for (var client : clients) {
//            System.out.println(client.toString());
//        }
//        return true;
//    }

    //    8. Add a new client

    public void addClient() {
        String sql = "insert into client values (null, ?, ?, ?, ?) ";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            System.out.println("Add a new client: ");
            Client client = new Client();
            client.read();
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getHomeCity());
            statement.setInt(4, client.getBalance());
            statement.executeUpdate();
            System.out.println("Client added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    9. Edit client information

    public void editClient() {
        showClients();
        System.out.print("Enter the id of the client you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (clientExists(id)) {
            String sql = "update client set name = ?, age = ?, homeCity = ?, balance = ? where idClient = ?";
            try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                System.out.println("Enter the new info: ");
                Client client = new Client();
                client.read();
                statement.setString(1, client.getName());
                statement.setInt(2, client.getAge());
                statement.setString(3, client.getHomeCity());
                statement.setInt(4, client.getBalance());
                statement.setInt(5, id);
                statement.executeUpdate();
                System.out.println("Client edited successfully");
                statement.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    10. Remove a client from the database

    public void removeClient() {
        if (showClients()) {
            System.out.print("\nChoose the id of the client you want to remove: ");
            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());
            if (clientExists(id)) {
                String sql = "delete from client where idClient = ?";
                try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    System.out.println("Client removed successfully");
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("There isn't any client with the id = " + id + "\n");
            }
        }
    }

    //Open garage (Show all the cars rented by a client)
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
    public void garage() {
        boolean flag = showClients();
        if (!flag)
            return;
        Client client = readClientName();
        boolean k = false;
        System.out.println(client.getName() + " rented the following cars: ");
        for (var carBooking : carBookings) {
            if (carBooking.getClient().equals(client)) {
                System.out.println(carBooking.getDuration() + " day rental - " + carBooking.getCar().toString());
                k = true;
            }
        }
        if (!k)
            System.out.println(client.getName() + " didn't rent any cars");
    }

    // 11. Rent a car

    public void rentCar () {
        if (!showClients())
            return;
        System.out.println("Enter the client id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (clientExists(id)) {
            if (!showCars())
                return;
            System.out.println("Enter the car id: ");
            scanner = new Scanner(System.in);
            int carid = Integer.parseInt(scanner.nextLine());
            if (carExists(carid)) {
                String sql = "select carPrice, rented from car where idCar = ?";
                try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, carid);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        int carPrice = resultSet.getInt(1);
                        int rented = resultSet.getInt(2);

                        if (rented == 1) {
                            System.out.println("This car is not available");
                        } else {
                            System.out.print("Enter rental duration (in days): ");
                            scanner = new Scanner(System.in);
                            int duration = Integer.parseInt(scanner.nextLine());
                            int totalPrice = carPrice * duration;

                            String sql2 = "select balance from client where idClient = ?";
                            try (PreparedStatement statement2 = JDBC.getInstance().prepareStatement(sql2)) {
                                statement2.setInt(1, id);
                                ResultSet resultSet2 = statement2.executeQuery();
                                if (resultSet2.next()) {
                                    int balance = resultSet2.getInt(1);

                                    if (balance < totalPrice) {
                                        System.out.println("Not enough money");
                                    } else {
                                        String sql3 = "insert into carbooking values (null, ?, ?, ?)";
                                        try (PreparedStatement statement3 = JDBC.getInstance().prepareStatement(sql3)) {
                                            statement3.setInt(1, carid);
                                            statement3.setInt(2, id);
                                            statement3.setInt(3, duration);
                                            statement3.executeUpdate();
                                        } catch(SQLException e) {
                                            e.printStackTrace();
                                        }

                                        String sql4 = "update car set rented = 1 where idCar = ?";
                                        try (PreparedStatement statement4 = JDBC.getInstance().prepareStatement(sql4)) {
                                            statement4.setInt(1, carid);
                                            statement4.executeUpdate();
                                        } catch(SQLException e) {
                                            e.printStackTrace();
                                        }

                                        String sql5 = "update client set balance = ? where idClient = ?";
                                        try (PreparedStatement statement5 = JDBC.getInstance().prepareStatement(sql5)) {
                                            statement5.setInt(1, balance - totalPrice);
                                            statement5.setInt(2, id);
                                            statement5.executeUpdate();
                                            System.out.println("Booking completed");
                                        } catch(SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }


                            } catch(SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                } catch(SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }

//    public void rentCar () {
//        boolean ff = showClients();
//        if (!ff)
//            return;
//        Client client = readClientName();
//        CarBooking carBooking = new CarBooking();
//        carBooking.setClient(client);
//
//        boolean flag = showCars();
//        if (flag) {
//            System.out.print("\nChoose the id of the car you want to rent: ");
//            Scanner r = new Scanner(System.in);
//            int id = Integer.parseInt(r.nextLine());
//
//            while (!carExists(id)) {
//                r = new Scanner(System.in);
//                id = Integer.parseInt(r.nextLine());
//            }
//            carBooking.read();
//            int totalPrice = 0;
//            for (var car : cars) {
//                if (car.getIdCar() == id) {
//                    totalPrice = car.getcarPrice() * carBooking.getDuration();
//                    break;
//                }
//            }
//
//            int balance = client.getBalance();
//            if (totalPrice <= balance) {
//                for (var car : cars) {
//                    if (car.getIdCar() == id) {
//                        carBooking.setCar(car);
//                        cars.remove(car);
//                        break;
//                    }
//                }
//                System.out.println("Car rented successfully!");
//                System.out.println("Total rental price: " + totalPrice + ". Old balance: " + balance + ". New balance: " + (balance - totalPrice));
//                client.setBalance(balance - totalPrice);
//                carBookings.add(carBooking);
//            }
//            else System.out.println("Not enough money!");
//        }
//    }

//    12. Show all car bookings

    public void showCarBookings () {
        String sql = "select * from carbooking";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Booking " + resultSet.getInt(1) + " - " + resultSet.getInt(4) + " days rental");
                carExists(resultSet.getInt(2));
                clientExists(resultSet.getInt(3));
                System.out.println("-----");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    MENU    //

    public void menuRentCar() {
        int option = -1;
        while (option != 0) {
            System.out.println("\nChoose one of the following actions:");
            System.out.println(" 1. Show all cars from the dealership");
            System.out.println(" 2. Search car by id");
            System.out.println(" 3. Add a new car to the dealership");
            System.out.println(" 4. Edit a car from the dealership");
            System.out.println(" 5. Remove a car from the dealership");
            System.out.println(" 6. Show all clients");
            System.out.println(" 7. Search client by id");
            System.out.println(" 8. Add a new client");
            System.out.println(" 9. Edit client information");
            System.out.println("10. Remove a client from the database");
            System.out.println("11. Rent a car (add a carbooking)");
            System.out.println("12. Show all car bookings");

//            System.out.println("Open garage (Show all the cars rented by a client)");
//            System.out.println("Cancel a car booking");
            System.out.println("------------------------------");
            System.out.println("0. Back to menu");

            Scanner input = new Scanner(System.in);
            option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1: {
                    showCars();
                    Audit.logAction("Show all cars from the dealership");
                    break;
                }
                case 2: {
                    System.out.println("Enter the car id: ");
                    Scanner scanner = new Scanner(System.in);
                    carExists(Integer.parseInt(scanner.nextLine()));
                    Audit.logAction("Search car by id");
                    break;
                }
                case 3: {
                    addCar();
                    Audit.logAction("Add a new car to the dealership");
                    break;
                }
                case 4: {
                    editCar();
                    Audit.logAction("Edit a car from the dealership");
                    break;
                }
                case 5: {
                    removeCar();
                    Audit.logAction("Remove a car from the dealership");
                    break;
                }
                case 6: {
                    showClients();
                    Audit.logAction("Show all clients");
                    break;
                }
                case 7: {
                    System.out.println("Enter the client id: ");
                    Scanner scanner = new Scanner(System.in);
                    clientExists(Integer.parseInt(scanner.nextLine()));
                    Audit.logAction("Search client by id");
                    break;
                }
                case 8: {
                    addClient();
                    Audit.logAction("Add a new client");
                    break;
                }
                case 9: {
                    editClient();
                    Audit.logAction("Edit client information");
                    break;
                }
                case 10: {
                    removeClient();
                    Audit.logAction("Remove a client from the database");
                    break;
                }
                case 11: {
                    rentCar();
                    Audit.logAction("Rent a car (add a carbooking)");
                    break;
                }
                case 12: {
                    showCarBookings();
                    Audit.logAction("Show all car bookings");
                    break;
                }
                default: {
                    System.out.print("\nPlease enter a valid option: ");
                }
            }
        }
    }


}


