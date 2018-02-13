package by.loiko.library.receiver;

import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;

import java.util.List;
import java.util.Map;


/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface BookReceiver extends AbstractReceiver {

    /**
     * Find book by title.
     *
     * @param title the title
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findBookByTitle(String title) throws ReceiverException;
    
    /**
     * Find books by array of books id.
     *
     * @param idArray the id array
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findBooksByArrayOfId(String[] idArray) throws ReceiverException;
    
    /**
     * Find books by genre id.
     *
     * @param id the id
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findBooksByGenreId(String id) throws  ReceiverException;
    
    /**
     * Find books by author id.
     *
     * @param id the id
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findBooksByAuthorId(String id) throws  ReceiverException;


    /**
     * Find all books (and deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findAllBooksAbs() throws ReceiverException;
    
    /**
     * Find all genres (not deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Genre> findAllGenres() throws ReceiverException;
    
    /**
     * Find all authors (not deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Author> findAllAuthors() throws ReceiverException;

    /**
     * Find all books (not deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Book> findAllBooks() throws ReceiverException;
    
    /**
     * Find all genres (and deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Genre> findAllGenresAbs() throws ReceiverException;
    
    /**
     * Find all authors (and deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Author> findAllAuthorsAbs() throws ReceiverException;

    /**
     * Find all not empty genres (where count books > 0).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Genre> findAllNotEmptyGenres() throws ReceiverException;
    
    /**
     * Find all not empty authors (where count books > 0).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<Author> findAllNotEmptyAuthors() throws ReceiverException;

    /**
     * Find book by id.
     *
     * @param id the id
     * @return the book
     * @throws ReceiverException the receiver exception
     */
    Book findBookById(String id) throws ReceiverException;
    
    /**
     * Find genre by id.
     *
     * @param id the id
     * @return the genre
     * @throws ReceiverException the receiver exception
     */
    Genre findGenreById(String id) throws ReceiverException;
    
    /**
     * Find author by id.
     *
     * @param id the id
     * @return the author
     * @throws ReceiverException the receiver exception
     */
    Author findAuthorById(String id) throws ReceiverException;

    /**
     * Adds the new book.
     *
     * @param paramsMap the params map
     * @param genres the genres
     * @param authors the authors
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> addNewBook(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException;
    
    /**
     * Adds the new genre.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> addNewGenre(Map<String, String> paramsMap) throws ReceiverException;
    
    /**
     * Adds the new author.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> addNewAuthor(Map<String, String> paramsMap) throws ReceiverException;

    /**
     * Change status genre to deleted.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void deleteGenre(String id) throws ReceiverException;
    
    /**
     * Change status author to deleted.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void deleteAuthor(String id) throws ReceiverException;
    
    /**
     * Change status book to deleted.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void deleteBook(String id) throws ReceiverException;

    /**
     * Update genre info.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> updateGenreInfo(Map<String, String> paramsMap) throws ReceiverException;
    
    /**
     * Update author info.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> updateAuthorInfo(Map<String, String> paramsMap) throws ReceiverException;

    /**
     * Update book info.
     *
     * @param paramsMap the params map
     * @param genres the genres
     * @param authors the authors
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> updateBookInfo(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException;



}
