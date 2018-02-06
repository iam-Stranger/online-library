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
    private EntityValidator validator = new EntityValidator();

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
        if (idTextArray == null){
            throw new ReceiverException("Wrong parameters in findBooksByArrayOfId method");
        }
        ArrayList<Long> idList = validator.validateArrayOfId(idTextArray);
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
    public Book findBookById(String id) throws ReceiverException {
        long bookId;
        if (validator.validateId(id)) {
            bookId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Book book;
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            List<Genre> genreList = genreDAO.findGenresByBookId(bookId);
            List<Author> authorList = authorDAO.findAuthorsByBookId(bookId);
            book = bookDAO.findEntityById(bookId);
            book.setGenres(genreList);
            book.setAuthors(authorList);
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
    public List<Genre> findAllGenresAbs() throws ReceiverException {
        List<Genre> genreList;
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genreList = genreDAO.findAllGenresAbs();
        } catch (DAOException e) {
            throw new ReceiverException("findAllGenres command wasn't executed:", e);
        }
        return genreList;
    }

    @Override
    public List<Author> findAllAuthors() throws ReceiverException {
        List<Author> authorList;

        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            authorList = authorDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findAllAuthors command wasn't executed:", e);
        }

        return authorList;
    }

    @Override
    public List<Author> findAllAuthorsAbs() throws ReceiverException {
        List<Author> authorList;

        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            authorList = authorDAO.findAllAuthorsAbs();
        } catch (DAOException e) {
            throw new ReceiverException("findAllAuthors command wasn't executed:", e);
        }

        return authorList;
    }

    @Override
    public List<Book> findAllBooksAbs() throws ReceiverException {
        return null;
    }

    @Override
    public Genre findGenreById(String id) throws ReceiverException {
        long genreId;
        if (validator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Genre genre;
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genre = genreDAO.findEntityById(genreId);
        } catch (DAOException e) {
            throw new ReceiverException("findGenreById command wasn't executed: ", e);
        }

        /// NULL genre

        return genre;
    }

    @Override
    public Author findAuthorById(String id) throws ReceiverException {
        long authorId;
        if (validator.validateId(id)) {
            authorId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Author author;
        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            author = authorDAO.findEntityById(authorId);
        } catch (DAOException e) {
            throw new ReceiverException("findAuthorById command wasn't executed: ", e);
        }

        return author;
    }

    @Override
    public Map<String, String> addNewBook(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("AddBook command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateBook(paramsMap, genres, authors);

        if (errorMap.isEmpty()) {
            try {
                bookDAO.addNewEntity(buildBook(paramsMap, genres, authors));
            } catch (DAOException e) {
                throw new ReceiverException("AddBook command wasn't executed: ", e);
            }
        }
        return errorMap;
    }

    @Override
    public Map<String, String> addNewGenre(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("AddGenre command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

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
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

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
    public void deleteGenre(String id) throws ReceiverException {
        long genreId;
        if (validator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            genreDAO.deleteEntityById(genreId);
        } catch (DAOException e) {
            throw new ReceiverException("deleteGenre command wasn't executed: ", e);
        }
    }

    @Override
    public void deleteAuthor(String id) throws ReceiverException {
        long authorId;
        if (validator.validateId(id)) {
            authorId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            authorDAO.deleteEntityById(authorId);
        } catch (DAOException e) {
            throw new ReceiverException("deleteAuthor command wasn't executed: ", e);
        }
    }

    @Override
    public void deleteBook(String id) throws ReceiverException {
        long bookId;
        if (validator.validateId(id)) {
            bookId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            bookDAO.deleteEntityById(bookId);
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
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

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
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                authorDAO.updateEntity(buildAuthor(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("UpdateAuthor command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    @Override
    public Map<String, String> updateBookInfo(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("UpdateBook command: data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateBook(paramsMap, genres, authors);

        if (errorMap.isEmpty()) {

            try {
                bookDAO.updateEntity(buildBook(paramsMap, genres, authors));
            } catch (DAOException e) {
                throw new ReceiverException("UpdateBook command wasn't executed: ", e);
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
        if (keyDeleted != null) {
            int intDeleted = Integer.parseInt(keyDeleted);
            genre.setIsDeleted(intDeleted != 0);
        }

        String keyType = paramsMap.get(FieldEnum.TYPE.toString().toLowerCase());
        if (keyType != null) {
            genre.setType(keyType);
        }

        return genre;
    }

    private Author buildAuthor(Map<String, String> paramsMap) {
        Author author = new Author();

        String keyId = paramsMap.get(FieldEnum.ID.toString().toLowerCase());
        if (keyId != null) {
            author.setId(Long.parseLong(keyId));
        }

        String keyDeleted = paramsMap.get(FieldEnum.STATUS.toString().toLowerCase());
        if (keyDeleted != null) {
            int intDeleted = Integer.parseInt(keyDeleted);
            author.setIsDeleted(intDeleted != 0);
        }

        author.setName(paramsMap.get(FieldEnum.NAME.toString().toLowerCase()));

        return author;
    }

    private Book buildBook(Map<String, String> paramsMap, String[] genres, String[] authors) {
        Book book = new Book();

        String keyId = paramsMap.get(FieldEnum.ID.toString().toLowerCase());
        if (keyId != null) {
            book.setId(Long.parseLong(keyId));
        }

        String keyDeleted = paramsMap.get(FieldEnum.STATUS.toString().toLowerCase());
        if (keyDeleted != null) {
            int intDeleted = Integer.parseInt(keyDeleted);
            book.setIsDeleted(intDeleted != 0);
        }

        book.setTitle(paramsMap.get(FieldEnum.TITLE.toString().toLowerCase()));
        book.setPublishYear(Integer.parseInt(paramsMap.get(FieldEnum.PUBLISH_YEAR.toString().toLowerCase())));
        book.setTotalAmount(Integer.parseInt(paramsMap.get(FieldEnum.TOTAL_AMOUNT.toString().toLowerCase())));
        book.setRealAmount(Integer.parseInt(paramsMap.get(FieldEnum.REAL_AMOUNT.toString().toLowerCase())));

        List<Genre> genreList = new ArrayList<>();
        for (String id : genres) {
            Genre genre = new Genre();
            genre.setId(Integer.parseInt(id));
            genreList.add(genre);
        }
        book.setGenres(genreList);

        List<Author> authorList = new ArrayList<>();
        for (String id : authors) {
            Author author = new Author();
            author.setId(Integer.parseInt(id));
            authorList.add(author);
        }
        book.setAuthors(authorList);

        return book;
    }

}
