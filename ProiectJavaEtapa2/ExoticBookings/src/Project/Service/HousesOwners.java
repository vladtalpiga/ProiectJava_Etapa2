package Project.Service;

import Project.Database.Audit;
import Project.Database.JDBC;
import Project.People.Owner;
import Project.Properties.House;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HousesOwners {
    public HousesOwners (){}

    // 1. Show all houses
    public boolean showHouses() {
        String sql = "select * from house";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                k = true;
                System.out.print("House " + resultSet.getInt(1) + ". Location: " + resultSet.getString(4) + ", costs " + resultSet.getInt(3) + " per night, owned by: ");
                int idOwner = resultSet.getInt(2);
                ownerExists(idOwner);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There are no available houses");
        return k;
    }
    // 2. Search house by id
    public boolean houseExists (int id) {
        String sql = "select * from house where idHouse = ?";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                k = true;
                System.out.print("House " + resultSet.getInt(1) + ". Location: " + resultSet.getString(4) + ", costs " + resultSet.getInt(3) + " per night, owned by: ");
                int idOwner = resultSet.getInt(2);
                ownerExists(idOwner);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There isn't any house with the id = " + id + "\n");
        return k;
    }

    // 3. Show all houses from a city
    public boolean housesFromCity (String city) {
        String sql = "select * from house where city = ?";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                k = true;
                System.out.print("House " + resultSet.getInt(1) + ". Location: " + resultSet.getString(4) + ", costs " + resultSet.getInt(3) + " per night, owned by: ");
                int idOwner = resultSet.getInt(2);
                ownerExists(idOwner);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There are no available houses in " + city + "\n");
        return k;
    }


    // 4. Add a new house
    public void addHouse() {
        String sql = "insert into house values (null, ?, ?, ?) ";
        String sql2 = "select nrProp from owner where idOwner = ?";
        String sql3 = "update owner set nrProp = ? where idOwner = ?";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            System.out.println("Add a new house: ");
            House house = new House();
            showOwners();
            house.read();
            int ownerid = house.getOwnerId();
            statement.setInt(1, ownerid);
            statement.setInt(2, house.getPrice());
            statement.setString(3, house.getCity());
            statement.executeUpdate();
            try (PreparedStatement statement2 = JDBC.getInstance().prepareStatement(sql2)) {
                statement2.setInt(1, ownerid);
                ResultSet resultSet = statement2.executeQuery();

                if (resultSet.next()) {
                    int nrProp = resultSet.getInt(1);

                    try (PreparedStatement statement3 = JDBC.getInstance().prepareStatement(sql3)) {
                        statement3.setInt(1, nrProp + 1);
                        statement3.setInt(2, ownerid);
                        statement3.executeUpdate();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }

            System.out.println("House added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    5. Edit a house
    public void editHouse() {
        showHouses();
        System.out.print("Enter the id of the house you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (houseExists(id)) {
            String sql = "update house set price = ?, city = ? where idHouse = ?";
            try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                System.out.println("Enter the new info: ");
                House house = new House();
                house.readWithoutOwner();
                statement.setInt(1, house.getPrice());
                statement.setString(2, house.getCity());
                statement.setInt(3, id);
                statement.executeUpdate();
                System.out.println("House edited successfully");
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 6. Remove a house
    public void removeHouse() {
        if (showHouses()) {
            System.out.print("\nChoose the id of the house you want to remove: ");
            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());
            if (houseExists(id)) {
                String sql = "delete from house where idHouse = ?";
                try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    System.out.println("House removed successfully");
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("There is no available house with id = " + id + "\n");
            }
        }
    }

    // 7. Show all owners
    public boolean showOwners() {
        String sql = "select * from owner";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                k = true;
                System.out.println("Owner " + resultSet.getInt(1) + ": " + resultSet.getString(2) + ", aged " + resultSet.getInt(3) + ", owns " + resultSet.getInt(4) + " houses");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There aren't any owners");
        return k;
    }

    //    8. Search owner by id
    public boolean ownerExists (int id) {
        String sql = "select * from owner where idOwner = ?";
        boolean k = false;
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                k = true;
//                System.out.println("There is an owner with the id = " + id + ": ");
                System.out.println("Owner " + resultSet.getInt(1) + ". " + resultSet.getString(2) + ", aged " + resultSet.getInt(3) + ", owns " + resultSet.getInt(4) + " houses");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if (!k)
            System.out.println("There isn't any owner with the id = " + id + "\n");
        return k;
    }

    //    9. Add a new owner
    public void addOwner() {
        String sql = "insert into owner values (null, ?, ?, 0) ";
        try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
            System.out.println("Add a new owner: ");
            Owner owner = new Owner();
            owner.read();
            statement.setString(1, owner.getName());
            statement.setInt(2, owner.getAge());
            statement.executeUpdate();
            System.out.println("Owner added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    10. Edit owner information

    public void editOwner() {
        showOwners();
        System.out.print("Enter the id of the owner you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if (ownerExists(id)) {
            String sql = "update owner set name = ?, age = ? where idOwner = ?";
            try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                System.out.println("Enter the new info: ");
                Owner owner = new Owner();
                owner.read();
                statement.setString(1, owner.getName());
                statement.setInt(2, owner.getAge());
                statement.setInt(3, id);
                statement.executeUpdate();
                System.out.println("Owner edited successfully");
                statement.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    11. Remove an owner from the database

    public void removeOwner() {
        if (showOwners()) {
            System.out.print("\nChoose the id of the owner you want to remove: ");
            Scanner r = new Scanner(System.in);
            int id = Integer.parseInt(r.nextLine());
            if (ownerExists(id)) {
                String sql = "delete from owner where idOwner = ?";
                try (PreparedStatement statement = JDBC.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    System.out.println("Owner removed successfully");
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("There isn't any owner with the id = " + id + "\n");
            }
        }
    }

    ///    MENU    ///
    public void menuHouseOwners() {
        int option = -1;
        while (option != 0) {
            System.out.println("\nChoose one of the following actions:");
            System.out.println(" 1. Show all houses");
            System.out.println(" 2. Search house by id");
            System.out.println(" 3. Show all houses from a city");
            System.out.println(" 4. Add a new house");
            System.out.println(" 5. Edit a house");
            System.out.println(" 6. Remove a house");
            System.out.println(" 7. Show all owners");
            System.out.println(" 8. Search owner by id");
            System.out.println(" 9. Add a new owner");
            System.out.println("10. Edit owner information");
            System.out.println("11. Remove an owner from the database");

            System.out.println("------------------------------");
            System.out.println("0. Back to menu");

            Scanner input = new Scanner(System.in);
            option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1: {
                    showHouses();
                    Audit.logAction("Show all houses");
                    break;
                }
                case 2: {
                    System.out.println("Enter the house id: ");
                    Scanner scanner = new Scanner(System.in);
                    houseExists(Integer.parseInt(scanner.nextLine()));
                    Audit.logAction("Search house by id");
                    break;
                }
                case 3: {
                    System.out.print("Search city: ");
                    Scanner scanner = new Scanner(System.in);
                    housesFromCity(scanner.nextLine());
                    Audit.logAction("Show all houses from a city");
                    break;
                }
                case 4: {
                    addHouse();
                    Audit.logAction("Add a new house");
                    break;
                }
                case 5: {
                    editHouse();
                    Audit.logAction("Edit a house");
                    break;
                }
                case 6: {
                    removeHouse();
                    Audit.logAction("Remove a house");
                    break;
                }
                case 7: {
                    showOwners();
                    Audit.logAction("Show all owners");
                    break;
                }
                case 8: {
                    System.out.println("Enter the owner id: ");
                    Scanner scanner = new Scanner(System.in);
                    ownerExists(Integer.parseInt(scanner.nextLine()));
                    Audit.logAction("Search owner by id");
                    break;
                }
                case 9: {
                    addOwner();
                    Audit.logAction("Add a new owner");
                    break;
                }
                case 10: {
                    editOwner();
                    Audit.logAction("Edit owner information");
                    break;
                }
                case 11: {
                    removeOwner();
                    Audit.logAction("Remove an owner from the database");
                    break;
                }
                default: {
                    System.out.print("\nPlease enter a valid option: ");
                }
            }
        }
    }
}
