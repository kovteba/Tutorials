package connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectToDBFilePropertiesTest {

    private static final Connection CONNECT_TO_DB;

    static {
        CONNECT_TO_DB = ConnectToDBFileProperties.connect();
    }

    @Test
    void connect() {
        if (CONNECT_TO_DB != null){
            System.out.println("OK");
        }
    }

}