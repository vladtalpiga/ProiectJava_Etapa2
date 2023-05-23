package Project.Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBC {
    private static Connection connection;

    private JDBC (){}

    public static Connection getInstance() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            if(connection == null) {
                String url = "jdbc:mysql://localhost:3306/jdbc";
                String username = "root";
                String password = "root";
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }



//    public static void main(String[] args) {
//        JDBC db = new JDBC();
//        db.createConnection();
//    }
//
//    void createConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/jdbc";
//            String username = "root";
//            String password = "root";
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from car");
//            while(resultSet.next()) {
//                System.out.println(resultSet.getInt(1) + ", " + resultSet.getInt(2) + ", " + resultSet.getString(3) + ", " + resultSet.getInt(4));
//            }
//
//
//            System.out.println("Database Connection Success");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }




//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/jdbc";
//        String username = "root";
//        String password = "";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from Client");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1) + ", " + resultSet.getInt(2) + ", " + resultSet.getString(3) + ", " + resultSet.getInt(4));
//            }
//
//            connection.close();
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
