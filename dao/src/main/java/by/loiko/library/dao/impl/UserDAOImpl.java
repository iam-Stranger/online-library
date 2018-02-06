package by.loiko.library.dao.impl;

import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
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
public class UserDAOImpl implements UserDAO {
    private static Logger logger = LogManager.getLogger();

    private final static String ADD_NEW_USER_SIGN_UP = "INSERT INTO user  (login, password, email, firstname, lastname) VALUES (?, ?, ?, ?, ?)";

    private final static String FIND_ID_BY_LOGIN = "SELECT id FROM user WHERE login = ?";
    private final static String FIND_ID_BY_EMAIL = "SELECT id FROM user WHERE email = ?";
    private final static String FIND_ALL_USERS = "SELECT * FROM user";
    private final static String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE deleted = 0 AND login = ? AND password = ?";

    @Override
    public List<User> findAllEntities() throws DAOException {
        List<User> userList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllEntities method ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return userList;
    }

    @Override
    public User findEntityById(long id) throws DAOException {
        User user = null;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findUserById method: ", e);
        }

        return user;
    }

    @Override
    public void deleteEntityById(long id) throws DAOException {

    }

    @Override
    public void addNewEntity(User user) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        boolean isCreate;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(ADD_NEW_USER_SIGN_UP);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DAOException("Error in addNewUser method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public void updateEntity(User user) throws DAOException {

    }

    @Override
    public List<User> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        return null;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = buildUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findUserByLoginAndPassword method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return user;
    }

    @Override
    public boolean isUserPresentByLogin(String login) throws DAOException {
        boolean isPresent = false;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_ID_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isPresent = true;
            }

        } catch (SQLException e) {
            throw new DAOException("Error in isUserPresentByLogin method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return isPresent;
    }

    @Override
    public boolean isUserPresentByEmail(String email) throws DAOException {
        boolean isPresent = false;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_ID_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isPresent = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in isUserPresentByEmail method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return isPresent;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        // user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setIsDeleted(resultSet.getBoolean("deleted"));

        return user;
    }

}
