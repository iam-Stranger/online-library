package by.loiko.library.dao;

import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface UserDAO extends AbstractDAO<User> {

    User findUser(String login, String password) throws DAOException;

    void addNewUser(String login, String password, String email, String firstName,
                    String lastName, int roleId, boolean enabled) throws DAOException;

}
