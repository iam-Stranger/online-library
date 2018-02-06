package by.loiko.library.dao;

import by.loiko.library.dao.impl.*;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();
    private final GenreDAO genreDAO = new GenreDAOImpl();
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    private final BookOrderDAO bookOrderDAO = new BookOrderDAOImpl();

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
