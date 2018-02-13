package by.loiko.library.dao;

import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;


/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface UserDAO extends AbstractDAO<User> {

    /**
     * Find user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws DAOException the DAO exception
     */
    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    /**
     * Checks if user present by login.
     *
     * @param login the login
     * @return true, if is user present by login
     * @throws DAOException the DAO exception
     */
    boolean isUserPresentByLogin(String login) throws DAOException;

    /**
     * Checks if user present by email.
     *
     * @param email the email
     * @return true, if is user present by email
     * @throws DAOException the DAO exception
     */
    boolean isUserPresentByEmail(String email) throws DAOException;

    /**
     * Change user password.
     *
     * @param userId the user id
     * @param newPassword the new password
     * @throws DAOException the DAO exception
     */
    void changeUserPassword(long userId, String newPassword) throws DAOException;
}
