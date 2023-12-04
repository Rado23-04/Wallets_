package Main.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL1 = System.getenv("URl");
    private static final String USER1 = System.getenv("USER");
    private static final String PASSWORD1 =  System.getenv("PASSWORD");

    public static Connection haveConnection() throws SQLException {
        return DriverManager.getConnection(URL1,USER1, PASSWORD1);
    }

    public static void connectionTest () {
        try(Connection connection = haveConnection()) {
            System.out.println("Succes Connection");
        } catch (SQLException e) {
            System.err.println("connection error: " + e.getMessage());
        }
    }
}
