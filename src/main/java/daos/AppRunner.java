package daos;

import models.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppRunner {

    public static final String databaseURL = "jdbc:mysql://localhost:3306/DAO_Lab?serverTimezone=UTC";
    public static final String user = "muhammeta7";
    public static final String password = "Paswerd7?";

    public static void main(String[] argv) {
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password);) {
            CarDAO carDAO = new CarDAO(connection);
            System.out.println("Great Success");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
