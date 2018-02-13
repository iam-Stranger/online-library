package by.loiko.library.dao;

import by.loiko.library.dao.impl.*;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class DAOFactory {
    
    /** The Constant instance. */
    private static final DAOFactory instance = new DAOFactory();

    /** The user DAO. */
    private final UserDAO userDAO = new UserDAOImpl();
    
    /** The book DAO. */
    private final BookDAO bookDAO = new BookDAOImpl();
    
    /** The genre DAO. */
    private final GenreDAO genreDAO = new GenreDAOImpl();
    
    /** The author DAO. */
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    
    /** The book order DAO. */
    private final BookOrderDAO bookOrderDAO = new BookOrderDAOImpl();

    /**
     * Instantiates a new DAO factory.
     */
    private DAOFactory() {
    }

    /**
     * Gets the single instance of DAOFactory.
     *
     * @return single instance of DAOFactory
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Gets the book DAO.
     *
     * @return the book DAO
     */
    public BookDAO getBookDAO() {
        return bookDAO;
    }

    /**
     * Gets the book order DAO.
     *
     * @return the book order DAO
     */
    public BookOrderDAO getBookOrderDAO() {
        return bookOrderDAO;
    }

    /**
     * Gets the genre DAO.
     *
     * @return the genre DAO
     */
    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    /**
     * Gets the author DAO.
     *
     * @return the author DAO
     */
    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }
}
