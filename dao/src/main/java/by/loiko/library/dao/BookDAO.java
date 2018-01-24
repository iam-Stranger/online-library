package by.loiko.library.dao;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface BookDAO extends AbstractDAO<Book> {

    ArrayList<Book> findBooksByTitle(String title) throws DAOException; // List<Book> ??

    ArrayList<Book> findBooksByArrayOfId(ArrayList<Long> idList) throws DAOException;

    void addNewBook(String title, int publishYear, int amount) throws DAOException;

}
