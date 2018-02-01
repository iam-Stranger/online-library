package by.loiko.library.receiver.impl;

import by.loiko.library.dao.AuthorDAO;
import by.loiko.library.dao.BookDAO;
import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.GenreDAO;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookReceiver;
import by.loiko.library.validator.FieldEnum;
import by.loiko.library.validator.EntityValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class BookReceiverImpl implements BookReceiver {
    private BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

    @Override
    public List<Book> findBookByTitle(String title) throws ReceiverException {
        if ("".equals(title) || title == null) {
            throw new ReceiverException("Title of book is empty");
        }

        ArrayList<Book> booksList;
        try {
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
    public List<Book> findBooksByArrayOfId(String[] idTextArray) throws ReceiverException {
        ArrayList<Long> idList = validateArrayOfId(idTextArray);
        if (idList.isEmpty()) {
            throw new ReceiverException("Wrong parameters in findBooksByArrayOfId method");
        }

        List<Book> booksList;
        try {
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
    public List<Book> findAllBooks() throws ReceiverException {
        List<Book> bookList;

        try {
            bookList = bookDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findAllBooks command wasn't executed:", e);
        }

        return bookList;
    }

    @Override
    public Book findBookById(long id) throws ReceiverException {
        if (id <= 0) {
            throw new ReceiverException("Wrong format ID");
        }

        Book book;
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            List<Genre> genreList = genreDAO.findGenresByBookId(id);
            book = bookDAO.findEntityById(id);
            book.setGenres(genreList);
        } catch (DAOException | NullPointerException e) {
            throw new ReceiverException("findBookById command wasn't executed: ", e);
        }

        return book;
    }

    @Override
    public List<Genre> findAllGenres() throws ReceiverException {
        List<Genre> genreList;

        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genreList = genreDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findAllGenres command wasn't executed:", e);
        }

        return genreList;
    }

    @Override
    public List<Author> findAllAuthors() throws ReceiverException {
        List<Author> authorList;

        try {
            authorList = authorDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findAllAuthors command wasn't executed:", e);
        }

        return authorList;
    }

    @Override
    public Genre findGenreById(long id) throws ReceiverException {
        Genre genre;

        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genre = genreDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("findGenreById command wasn't executed: ", e);
        }

        return genre;
    }

    @Override
    public Author findAuthorById(long id) throws ReceiverException {
        Author author;

        try {

            author = authorDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("findAuthorById command wasn't executed: ", e);
        }

        return author;
    }

    @Override
    public Map<String, String> addNewBook(Map<String, String> paramsMap) throws ReceiverException {
        return null;
    }

    @Override
    public Map<String, String> addNewGenre(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("AddGenre command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateGenreParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
                genreDAO.addNewEntity(buildGenre(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("AddGenre command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    @Override
    public Map<String, String> addNewAuthor(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("AddAuthor command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateGenreParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
                authorDAO.addNewEntity(buildAuthor(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("AddAuthor command wasn't executed: ", e);
            }
        }

        return errorMap;
    }


    @Override
    public void deleteGenre(long id) throws ReceiverException {
        if (id <= 0) {
            throw new ReceiverException("Genre ID is incorrect: ");
        }

        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genreDAO.deleteEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("deleteGenre command wasn't executed: ", e);
        }
    }

    @Override
    public void deleteAuthor(long id) throws ReceiverException {
        try {
            authorDAO.deleteEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("deleteAuthor command wasn't executed: ", e);
        }
    }

    @Override
    public void deleteBook(long id) throws ReceiverException {
        try {
            bookDAO.deleteEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("deleteBook command wasn't executed: ", e);
        }
    }

    @Override
    public Map<String, String> updateGenreInfo(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("UpdateGenre command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateGenreParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
                genreDAO.updateEntity(buildGenre(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("UpdateGenre command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    @Override
    public Map<String, String> updateAuthorInfo(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("UpdateAuthor command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateGenreParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                authorDAO.updateEntity(buildAuthor(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("UpdateAuthor command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    private Genre buildGenre(Map<String, String> paramsMap) {
        Genre genre = new Genre();

        String keyId = paramsMap.get(FieldEnum.ID.toString().toLowerCase());
        if (keyId != null) {
            genre.setId(Long.parseLong(keyId));
        }

        String keyDeleted = paramsMap.get(FieldEnum.STATUS.toString().toLowerCase());
        if (keyId != null) {
            int intDeleted = Integer.parseInt(keyDeleted);
            genre.setIsDeleted(intDeleted != 0);
        }

        genre.setType(paramsMap.get(FieldEnum.TYPE.toString().toLowerCase()));

        return genre;
    }

    private Author buildAuthor(Map<String, String> paramsMap) {
        Author author = new Author();

        String keyId = paramsMap.get(FieldEnum.ID.toString().toLowerCase());
        if (keyId != null) {
            author.setId(Long.parseLong(keyId));
        }

        String keyDeleted = paramsMap.get(FieldEnum.STATUS.toString().toLowerCase());
        if (keyId != null) {
            int intDeleted = Integer.parseInt(keyDeleted);
            boolean isDeleted = (intDeleted != 0);
            author.setIsDeleted(isDeleted);
        }

        author.setName(paramsMap.get(FieldEnum.NAME.toString().toLowerCase()));

        return author;
    }

}
