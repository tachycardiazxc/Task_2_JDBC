package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            getDriver();
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ignored) {}
        return connection;
    }

    private static void getDriver() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
    }

}
