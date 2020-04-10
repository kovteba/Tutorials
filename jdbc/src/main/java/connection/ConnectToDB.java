package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToDB {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/motordeport";
    private static final String user = "root";
    private static final String password = "root!";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Store DB succesfull!");
            return connection;
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return null;
    }

}