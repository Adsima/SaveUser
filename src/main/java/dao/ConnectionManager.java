package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static config.Config.*;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                getProperty(DB_URL),
                getProperty(DB_LOGIN),
                getProperty(DB_PASSWORD));
    }
}
