package by.loiko.library.receiver;

import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface BookReceiver extends AbstractReceiver {

    ArrayList<Book> findBookByTitle(String title) throws ReceiverException;

    ArrayList<Book> findBooksByArrayOfId(String[] idArray) throws ReceiverException;

    Book findBookById(long id) throws ReceiverException;

    void addNewBook(String title, int publishYear, int amount) throws ReceiverException;


    List<Genre> findAllGenres() throws ReceiverException;
    List<Author> findAllAuthors() throws ReceiverException;

    Genre findGenreById(long id) throws ReceiverException;
    Author findAuthorById(long id) throws ReceiverException;

    boolean addNewGenre(Genre genre) throws ReceiverException;
    boolean addNewAuthor(Author author) throws ReceiverException;
    boolean addNewBook(Book book) throws ReceiverException;
}
