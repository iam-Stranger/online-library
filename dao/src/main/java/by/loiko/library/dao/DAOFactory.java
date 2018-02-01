package by.loiko.library.dao;

import by.loiko.library.dao.impl.*;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new MySQLUserDAO();
    private final BookDAO bookDAO = new MySQLBookDAO();
    private final GenreDAO genreDAO = new MySQLGenreDAO();
    private final AuthorDAO authorDAO = new MySQLAuthorDAO();
    private final BookOrderDAO bookOrderDAO = new MySQLBookOrderDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public BookOrderDAO getBookOrderDAO() {
        return bookOrderDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }
}
