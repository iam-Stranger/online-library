package by.loiko.library.dao;

import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface BookDAO extends AbstractDAO<Book> {

    ArrayList<Book> findBooksByTitle(String title) throws DAOException;

    List<Genre> findAllGenres() throws DAOException;
    List<Author> findAllAuthors() throws DAOException;

    Genre findGenreById(long id) throws DAOException;
    Author findAuthorById(long id) throws DAOException;

    boolean addNewGenre(Genre genre) throws DAOException;
    boolean addNewAuthor(Author author) throws DAOException;
    boolean addNewBook(Book book) throws DAOException;

}
