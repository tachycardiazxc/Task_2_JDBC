package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_2_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            getDriver();
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ignored) {
        }
        return connection;
    }

    private static void getDriver() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
    }

    public static SessionFactory setUpHibernate() throws HibernateException {
        Properties properties = new Properties();

        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/task_2_db");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "admin");
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        properties.setProperty(Environment.SHOW_SQL, "true");

        return getSession(properties);
    }

    private static SessionFactory getSession(Properties properties) throws HibernateException {
        return new org.hibernate.cfg.Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

}
