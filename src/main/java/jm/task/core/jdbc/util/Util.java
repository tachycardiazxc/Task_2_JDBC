package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    public static Session setUpHibernate() {
        SessionFactory sessionFactory = null;
        try {
            Properties properties = new Properties();

            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/task_2_db");
            properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "admin");
            properties.setProperty("hibernate.hbm2ddl.auto", "create");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            return getSession(sessionFactory);
        } catch (HibernateException ignored) {}
        return null;
    }

    private static Session getSession(SessionFactory sessionFactory) throws HibernateException {
        return sessionFactory.openSession();
    }

}
