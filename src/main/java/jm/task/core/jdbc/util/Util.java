package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static Properties setUpHibernate() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/task_2_db");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "admin");

        return properties;
    }

}
