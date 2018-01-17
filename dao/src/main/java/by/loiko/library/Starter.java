package by.loiko.library;

import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Starter {

    public static void main(String[] args) throws InterruptedException {

        DAOFactory daoFactory = DAOFactory.getInstance();

        UserDAO userDAO = daoFactory.getUserDAO();

        ArrayList<User> userList = null;
        try {
            userList = userDAO.findAll();
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

        for (User user: userList) {
            System.out.println(user.getLogin()+" "+user.getFirstName()+" "+user.getLastName());
        }

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroyConnectionPool();


    }
}
