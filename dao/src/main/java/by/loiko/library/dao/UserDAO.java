package by.loiko.library.dao;

import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface UserDAO extends AbstractDAO<User> {

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    boolean isUserPresentByLogin(String login) throws DAOException;
    boolean isUserPresentByEmail(String email) throws DAOException;

    boolean addNewUser(User user) throws DAOException;

}
