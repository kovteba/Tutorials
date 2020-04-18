package connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectToDBFileProperties {

    private static final Logger LOG = Logger.getLogger(ConnectToDBFileProperties.class.getName());

    private static final Properties PROPERTIES = new Properties();

    public static Connection connect() {

        try {

            FileInputStream fileWithPropertiesForDB
                    = new FileInputStream("/home/dima/Documents/IT/Tutorials/jdbc/src/main/resources/config.db.properties");

            PROPERTIES.load(fileWithPropertiesForDB);
            String url = PROPERTIES.getProperty("db.url");

            Properties properties = new Properties();
            properties.setProperty("user", PROPERTIES.getProperty("db.login"));
            properties.setProperty("password", PROPERTIES.getProperty("db.password"));
            properties.setProperty("serverTimezone", PROPERTIES.getProperty("db.serverTimeZone"));

            Connection connection = DriverManager.getConnection(url, properties);
            LOG.info("Connection to Store DB successfully!");

            return connection;

        } catch (Exception ex) {
            LOG.warning("Connection failed..." + ex);
        }

        return null;

    }

}
