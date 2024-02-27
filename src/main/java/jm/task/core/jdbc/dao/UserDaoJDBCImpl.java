package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = Util.open();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY, 
                name VARCHAR(100),
                lastname VARCHAR(100),
                age INT)
                """;
        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){
            System.out.println("Таблица не создалась "+ e);
        }
    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println("Не удалось удалить таблицу "+ e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO users (
                name,
                lastname,
                age)
                VALUES(
                ?,
                ?,
                ?)
                """;
        try(PreparedStatement ppst = connection.prepareStatement(sql)){
            ppst.setString(1,name);
            ppst.setString(2,lastName);
            ppst.setByte(3,age);
            ppst.executeUpdate();
            System.out.println("User с именем – " + name +  " добавлен в базу данных.");
        }catch (SQLException e){
            System.out.println("Не удалось добавить User "+ e);}
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM users WHERE  id = ?
                """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.out.println("Не удалось удалить User "+e);
            }
        }

    public List<User> getAllUsers() {
        String sql = """
                SELECT * FROM users
                """;
            List<User> users = new ArrayList<>();
            try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
            }catch (SQLException e){
                System.out.println("При попытке достать всех пользователей из базы данных произошло исключение " + e);
                }
        return users;
    }

    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE  users
                """;
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
            }catch (SQLException e){
                System.out.println("При тестировании очистки таблицы пользователей произошло исключение " + e);
        }

    }
}
