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

        if (login.equals("") || password.equals("")) {
            throw new ReceiverException("login or password is empty");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findUser(login, password);
        } catch (DAOException e) {
            throw new ReceiverException("findUser command wasn't executed: ", e);
        }

        if (user == null) {
            throw new ReceiverException("Login or password is incorrect");
        }

        return user;
    }

    @Override
    public boolean signIn(String login, String password) throws ReceiverException {
        return false;
    }
}
