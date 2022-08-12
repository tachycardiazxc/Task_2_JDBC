package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/task_2_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    private static final String[] names = {"Alexey", "Andy", "Paj", "Hideo"};
    private static final String[] lastNames = {"Kuznetsov", "Walker", "Pajero", "Takayama"};
    private static final byte[] ages = {32, 19, 98, 60};

    private static final String INSERT_NEW = "INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)";

    public static void main(String[] args) {

        ArrayList<User> users = createUsersList(names, lastNames, ages);

        try (Connection connection = Util.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW)) {
            addUsersToTable(users, preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addUsersToTable(ArrayList<User> users, PreparedStatement preparedStatement) {
        users.forEach(x -> {
            try {
                preparedStatement.setString(1, x.getName());
                preparedStatement.setString(2, x.getLastName());
                preparedStatement.setInt(3, x.getAge());

                preparedStatement.execute();

                System.out.printf("User с именем - %s добавлен в базу данных", x.getName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static ArrayList<User> createUsersList(String[] names, String[] lastNames, byte[] ages) {
        ArrayList<User> usersList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            usersList.add(new User(names[i], lastNames[i], ages[i]));
        }
        return usersList;
    }
}
