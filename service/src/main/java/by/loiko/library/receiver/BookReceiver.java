package by.loiko.library.receiver;

import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import java.util.List;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface BookReceiver extends AbstractReceiver {

    List<Book> findBookByTitle(String title) throws ReceiverException;
    List<Book> findBooksByArrayOfId(String[] idArray) throws ReceiverException;

    List<Book> findAllBooks() throws ReceiverException;
    List<Genre> findAllGenres() throws ReceiverException;
    List<Author> findAllAuthors() throws ReceiverException;

    Book findBookById(long id) throws ReceiverException;
    Genre findGenreById(long id) throws ReceiverException;
    Author findAuthorById(long id) throws ReceiverException;

    Map<String, String> addNewBook(Map<String, String> paramsMap) throws ReceiverException;
    Map<String, String> addNewGenre(Map<String, String> paramsMap) throws ReceiverException;
    Map<String, String> addNewAuthor(Map<String, String> paramsMap) throws ReceiverException;

    void deleteGenre(long id) throws ReceiverException;
    void deleteAuthor(long id) throws ReceiverException;
    void deleteBook(long id) throws ReceiverException;

    Map<String, String> updateGenreInfo(Map<String, String> paramsMap) throws ReceiverException;
    Map<String, String> updateAuthorInfo(Map<String, String> paramsMap) throws ReceiverException;



}
