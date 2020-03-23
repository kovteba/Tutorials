package connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectToDBTest {

    private static Connection connectToDB;

    static {
        connectToDB = ConnectToDB.connect();
    }

    @Test
    void connect() {
        if (connectToDB != null){
            System.out.println("OK");
        }
    }
}