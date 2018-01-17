package by.loiko.library.dao;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface BookDAO extends AbstractDAO<Book> {

    Book findBookByTitle(String title) throws DAOException; // List<Book> ??

    void addNewBook(String title, int publishYear, int amount) throws DAOException;

}
