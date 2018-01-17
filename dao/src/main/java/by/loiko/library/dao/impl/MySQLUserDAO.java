package by.loiko.library.dao.impl;

import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class MySQLUserDAO implements UserDAO {
    //private static Logger logger = LogManager.getLogger();

    private final static String FIND_ALL_USERS = "SELECT * FROM user";
    private final static String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";

    @Override
    public ArrayList<User> findAll() throws DAOException {
        ArrayList<User> userList = new ArrayList<>(); // ??????
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAll method ", e);
        } finally {
            close(statement);
            releaseConnection(connection);
        }

        return userList;
    }

    @Override
    public User findEntityById() throws DAOException {
        return null;
    }

    @Override
    public void deleteEntityById(long id) throws DAOException {

    }

    @Override
    public User findUser(String login, String password) throws DAOException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findUser method: ", e);
        } finally {
            close(statement);
            releaseConnection(connection);
        }

        return user;
    }

    @Override
    public void addNewUser(String login, String password, String email, String firstName, String lastName, int roleId, boolean enabled) {

    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));  // ????
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setEnabled(resultSet.getBoolean("enabled"));

        return user;
    }

}
