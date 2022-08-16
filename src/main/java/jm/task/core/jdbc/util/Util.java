package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/task_2_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            getDriver();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ignored) {}
        return connection;
    }

    private static void getDriver() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
    }

}
