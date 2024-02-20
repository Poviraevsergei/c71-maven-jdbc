package com.tms.repository;

import com.tms.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final static String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private final static String SELECT_USER_FROM_USERS = "SELECT * FROM users WHERE id=?";
    private final static String INSERT_USER_INTO_USERS = "INSERT INTO users(id,username,user_password,created,changed,age)" +
            " VALUES(DEFAULT,?,?,?,?,?)";
    private final static String UPDATE_USER_INTO_USERS = "UPDATE users SET username=?, user_password=?, changed=?, age=? WHERE id=?";
    private final static String REMOVE_USER_FROM_USERS = "DELETE FROM users WHERE id=?";
    private final static String MOST_OLDER_USER = "{? = call max_old_in_the_users()}";
    private final static String TRUNCATE_TELEPHONE_TABLE = "call truncate_telephone_table()";
    private Connection connection = null;
    //CRUD


    public UserRepository() {
        try {
            //1. Регистрация драйвера
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/c71_database", "postgres", "root");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USERS);

            while (resultSet.next()) {
                User user = parseUser(resultSet);
                if (user.getId() != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    //CRUD
    //READ - достать обьект из БД
    public User readUser(Long id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_FROM_USERS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = parseUser(resultSet);
                if (user.getId() != null) {
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    //CREATE - обавление в таблицу
    public boolean createUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_INTO_USERS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserPassword());
            statement.setTimestamp(3, user.getCreated());
            statement.setTimestamp(4, user.getChanged());
            statement.setInt(5, user.getAge());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //UPDATE - обновляет юзера
    public boolean updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INTO_USERS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserPassword());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, user.getAge());
            statement.setLong(5, user.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //DELETE - удаляет пользователя из БД
    public boolean deleteUser(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(REMOVE_USER_FROM_USERS);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public User parseUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setUserPassword(resultSet.getString("user_password"));
            user.setUsername(resultSet.getString("username"));
            user.setCreated(resultSet.getTimestamp("created"));
            user.setChanged(resultSet.getTimestamp("changed"));
            user.setAge(resultSet.getInt("age"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public boolean checkTransaction() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statementAge = connection.prepareStatement("UPDATE users SET age=100 WHERE id=13");
            PreparedStatement statementUsername = connection.prepareStatement("UPDATE users SET username='USER_TRANSACTION' WHERE id=13");
            PreparedStatement statementPassword = connection.prepareStatement("UPDATE users SET user_password='USER_PASS' WHERE id=13");
            statementAge.executeUpdate();
            statementUsername.executeUpdate();
            statementPassword.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                System.out.println(exception);
            }

        }
        return false;
    }

    public String getUsernameOfTheMostOldUserFunction() {
        String resultUsername = null;
        try {
            CallableStatement statement = connection.prepareCall(MOST_OLDER_USER);
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.executeUpdate();
            resultUsername = statement.getString(1);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resultUsername;
    }

    public boolean truncateTelephoneTableFunction() {
        try {
            CallableStatement statement = connection.prepareCall(TRUNCATE_TELEPHONE_TABLE);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}