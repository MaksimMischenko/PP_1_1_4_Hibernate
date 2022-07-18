package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), lastName VARCHAR(45), age TINYINT(100));";
        try {
            connection.setAutoCommit(false);
            connection.createStatement().execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

            String sql = "DROP TABLE IF EXISTS User";
            try {
                connection.setAutoCommit(false);
                connection.createStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO User (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
        try {
            connection.setAutoCommit(false);
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM User WHERE id = "+id+";";
        try {
            connection.setAutoCommit(false);
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM User";
        ResultSet res = null;
        try {
            connection.setAutoCommit(false);
            res = connection.createStatement().executeQuery(sql);
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong(1));
                user.setName(res.getString(2));
                user.setLastName(res.getString(3));
                user.setAge(res.getByte(4));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM User";
        try {
            connection.setAutoCommit(false);
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
