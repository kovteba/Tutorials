package connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectToDBTest {

    private static final Connection CONNECT_TO_DB;

    static {
        CONNECT_TO_DB = ConnectToDB.connect();
    }

    @Test
    void connect() {
        if (CONNECT_TO_DB != null){
            System.out.println("OK");
        }
    }

}