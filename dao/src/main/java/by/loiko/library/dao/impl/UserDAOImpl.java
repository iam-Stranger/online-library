package by.loiko.library.dao.impl;

import by.loiko.library.creator.UserCreator;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.dao.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;

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
    /* ResultSetCreator for Book objects */
    private UserCreator userCreator = new UserCreator();

    private final static String ADD_NEW_USER_SIGN_UP = "INSERT INTO user  (login, password, email, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
    private final static String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private final static String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private final static String FIND_ALL_USERS = "SELECT * FROM user";
    private final static String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE deleted = 0 AND login = ? AND password = ?";
    /* MySQL query change status User to deleted */
    private final static String DELETE_USER = "UPDATE user SET deleted = '1' WHERE id = ?";
    private final static String UPDATE_USER = "UPDATE user SET login = ?, email = ?, firstname = ?, lastname = ?, role_id = ?, deleted = ? WHERE id = ?";
    private final static String CHANGE_PASSWORD = "UPDATE user SET password = ? WHERE id = ?";

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findAllEntities()
     */
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
                userList.add(userCreator.createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllEntities method " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return userList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntityById(long)
     */
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
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findUserById method: " + e.getMessage(), e);
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#deleteEntityById(long)
     */
    @Override
    public void deleteEntityById(long id) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(DELETE_USER);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delUser method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#addNewEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void addNewEntity(User user) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(ADD_NEW_USER_SIGN_UP);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error in addNewUser method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#updateEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void updateEntity(User user) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(UPDATE_USER);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getRoleId());
            statement.setBoolean(6, user.getIsDeleted());
            statement.setLong(7, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error in updateUser method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntitiesByArrayOfId(java.util.List)
     */
    @Override
    public List<User> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.UserDAO#findUserByLoginAndPassword(java.lang.String, java.lang.String)
     */
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
                user = userCreator.createUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findUserByLoginAndPassword method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.UserDAO#findUserByLogin(java.lang.String)
     */
    @Override
    public User findUserByLogin(String login) throws DAOException {
       User user = null;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findUserByLogin method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.UserDAO#findUserByEmail(java.lang.String)
     */
    @Override
    public User findUserByEmail(String email) throws DAOException {
       User user = null;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = userCreator.createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findUserByEmail method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.UserDAO#changeUserPassword(long, java.lang.String)
     */
    @Override
    public void changeUserPassword(long userId, String newPassword) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in changeUserPassword method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

}
