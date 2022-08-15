package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    private static final String[] names = {"Alexey", "Andy", "Paj", "Hideo"};
    private static final String[] lastNames = {"Kuznetsov", "Walker", "Pajero", "Takayama"};
    private static final byte[] ages = {32, 19, 98, 60};

    public static void main(String[] args) {
        ArrayList<User> users = createUsersList(names, lastNames, ages);
    }

    public static ArrayList<User> createUsersList(String[] names, String[] lastNames, byte[] ages) {
        ArrayList<User> usersList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            usersList.add(new User(names[i], lastNames[i], ages[i]));
        }
        return usersList;
    }
}
