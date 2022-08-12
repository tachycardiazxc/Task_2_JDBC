package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/task_2_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {

        try {
            Connection connection = Util.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
