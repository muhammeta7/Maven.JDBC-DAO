package daos;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppRunner {

    private static final Logger LOGGER =
            Logger.getLogger(AppRunner.class.getName());
    public static final String databaseURL = "jdbc:mysql://localhost:3306/DAO_Lab?serverTimezone=UTC";
    public static final String user = "muhammeta7";
    public static final String password = "Paswerd7?";

    public static void main(String[] argv) {
        Connection connection = AppRunner.getConnection();
    }

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            LOGGER.log(Level.INFO, "Great Success!");
            return DriverManager.getConnection(databaseURL, user, password);
        } catch(SQLException e){
            LOGGER.info("NAH SON" + e);
            throw new RuntimeException("Can't Connect", e);
        }
    }


}
