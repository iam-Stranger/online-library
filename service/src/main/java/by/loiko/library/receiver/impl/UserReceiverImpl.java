package by.loiko.library.receiver.impl;

import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.UserReceiver;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class UserReceiverImpl implements UserReceiver {
    @Override
    public ArrayList<User> findAllUsers() throws ReceiverException {
        ArrayList<User> userList = null;

        try {
            DAOFactory daoFactoryObj = DAOFactory.getInstance();
            UserDAO userDAO = daoFactoryObj.getUserDAO();
            userList = userDAO.findAll();
        } catch (DAOException e) {
            throw new ReceiverException("findAllUsers command wasn't executed: ", e);
        }

        return userList;
    }

    @Override
    public User findUser(String login, String password) throws ReceiverException {

        if ("".equals(login) || "".equals(password) || login == null || password == null) {
            throw new ReceiverException("login or password is empty");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ReceiverException("findUserByLoginAndPassword command wasn't executed: ", e);
        }

        if (user == null) {
            throw new ReceiverException("Login or password is incorrect");
        }

        return user;
    }

    @Override
    public User findUserById(long id) throws ReceiverException {
        if (id <= 0) {
            throw new ReceiverException("User ID is incorrect");
        }

        User user;

        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("findUserById command error: ", e);
        }

        if (user == null) {
            throw new ReceiverException("User with this id is not found");
        }

        return user;
    }

    @Override
    public boolean signIn(String login, String password) throws ReceiverException {
        return false;
    }
}
