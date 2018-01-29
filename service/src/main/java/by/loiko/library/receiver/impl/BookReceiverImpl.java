package by.loiko.library.receiver.impl;

import by.loiko.library.dao.BookDAO;
import by.loiko.library.dao.DAOFactory;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookReceiver;

import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class BookReceiverImpl implements BookReceiver {
    @Override
    public ArrayList<Book> findBookByTitle(String title) throws ReceiverException {

        if ("".equals(title) || title == null) {
            throw new ReceiverException("Title of book is empty");
        }

        ArrayList<Book> booksList;

        try {
            BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
            booksList = bookDAO.findBooksByTitle(title);
        } catch (DAOException e) {
            throw new ReceiverException("findBooksByTitle command wasn't executed: ", e);
        }

        if (booksList.isEmpty()) {
            throw new ReceiverException("Books with this title wasn't found");
        }

        return booksList;
    }

    @Override
    public ArrayList<Book> findBooksByArrayOfId(String[] idTextArray) throws ReceiverException {
        ArrayList<Long> idList = validateArrayOfId(idTextArray);
        if (idList.isEmpty()) {
            throw new ReceiverException("Wrong parameters in findBooksByArrayOfId method");
        }

        ArrayList<Book> booksList;
        try {
            BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
            booksList = bookDAO.findEntitiesByArrayOfId(idList);
        } catch (DAOException e) {
            throw new ReceiverException("findBooksByArrayOfId command wasn't executed: ", e);
        }

        if (booksList.isEmpty()) {
            throw new ReceiverException("Books with this id wasn't found");
        }

        return booksList;
    }

    @Override
    public Book findBookById(long id) throws ReceiverException {
        if (id <= 0) {
            throw new ReceiverException("Wrong format ID");
        }

        Book book;
        try {
            BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
            book = bookDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("findBookById command wasn't executed: ", e);
        }

        if (book == null) {
            throw new ReceiverException("Book with this ID is absent");
        }

        return book;
    }

    @Override
    public void addNewBook(String title, int publishYear, int amount) throws ReceiverException {

    }

    @Override
    public List<Genre> findAllGenres() throws ReceiverException {
        List<Genre> genreList;

        try {
            BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
            genreList = bookDAO.findAllGenres();
        } catch (DAOException e) {
            throw new ReceiverException("findAllGenres command wasn't executed:", e);
        }

        return genreList;
    }

    @Override
    public List<Author> findAllAuthors() throws ReceiverException {
        List<Author> authorList;

        try {
            BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
            authorList = bookDAO.findAllAuthors();
        } catch (DAOException e) {
            throw new ReceiverException("findAllAuthors command wasn't executed:", e);
        }

        return authorList;
    }

    @Override
    public Genre findGenreById(long id) throws ReceiverException {
        return null;
    }

    @Override
    public Author findAuthorById(long id) throws ReceiverException {
        return null;
    }

    @Override
    public boolean addNewGenre(Genre genre) throws ReceiverException {
        return false;
    }

    @Override
    public boolean addNewAuthor(Author author) throws ReceiverException {
        return false;
    }

    @Override
    public boolean addNewBook(Book book) throws ReceiverException {
        return false;
    }

}
