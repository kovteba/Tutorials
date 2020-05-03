package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class ConnectToDB {

    private static final Logger LOG = Logger.getLogger(ConnectToDB.class.getName());

    // JDBC URL, username and password of MySQL server
    private static final String URL_DB = "jdbc:mysql://localhost:3306/motordeport";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String SERVER_TIMEZONE_UTC = "serverTimezone=UTC";

    public static Connection connect() {

        try {

            String url = URL_DB + "?" + SERVER_TIMEZONE_UTC;

            Connection connection = DriverManager.getConnection(url, USER, PASSWORD);

            LOG.info("Connection to Store DB successfully!");

            return connection;

        } catch (Exception ex) {
            LOG.warning("Connection failed..." + ex);
        }

        return null;
    }

}