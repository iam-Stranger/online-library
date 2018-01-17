package by.loiko.library.dao.impl;

import by.loiko.library.dao.BookDAO;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class MySQLBookDAO implements BookDAO {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ArrayList<Book> findAll() throws DAOException {
        return null;
    }

    @Override
    public Book findEntityById() throws DAOException {
        return null;
    }

    @Override
    public void deleteEntityById(long id) throws DAOException {

    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public void addNewBook(String title, int publishYear, int amount) {

    }
}
