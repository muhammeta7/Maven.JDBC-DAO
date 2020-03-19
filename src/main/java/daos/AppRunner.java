package daos;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AppRunner {

    public static final String databaseURL = "jdbc:mysql://localhost:3306/DAO_Lab?serverTimezone=UTC";
    public static final String user = "muhammeta7";
    public static final String password = "Paswerd7?";

    public static void main(String[] argv) {
        Connection connection = AppRunner.getConnection();
    }

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            System.out.println("Great Success");
            return DriverManager.getConnection(databaseURL, user, password);
        } catch(SQLException e){
            throw new RuntimeException("Can't Connect", e);
        }
    }


}
