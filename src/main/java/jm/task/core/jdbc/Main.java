package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Connection connection = Util.open();

        try {
            String query = "SELECT version()";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String version = resultSet.getString(1);
                System.out.println("PostgreSQL database version: " + version);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(connection);
        }
    }




}

