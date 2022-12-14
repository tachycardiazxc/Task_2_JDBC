package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String SQL_CREATE = "CREATE TABLE users " +
                    "(id INTEGER auto_increment, " +
                    "name VARCHAR(32) not null, " +
                    "last_name VARCHAR(32) not null, " +
                    "age INTEGER(3), " +
                    "constraint `PRIMARY` " +
                    "PRIMARY KEY (id))";
            connection.createStatement().executeUpdate(SQL_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            String SQL_DROP = "DROP TABLE users";
            connection.createStatement().executeUpdate(SQL_DROP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL_INSERT_NEW = "INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();

            System.out.printf("User with name - %s was added to db\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL_REMOVE_BY_ID = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_BY_ID);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException ignored) {}
    }

    public List<User> getAllUsers() {
        String SQL_GET_ALL_USERS = "SELECT * FROM users";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_ALL_USERS);

            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);

                System.out.println(user);
            }
            return userList;
        } catch (SQLException ignored) {}
        return null;
    }

    public void cleanUsersTable() {
        String SQL_CLEAN_TABLE = "DELETE FROM users";
        try {
            connection.createStatement().executeUpdate(SQL_CLEAN_TABLE);
        } catch (SQLException ignored) {}
    }
}
