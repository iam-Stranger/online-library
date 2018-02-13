package by.loiko.library.receiver.impl;

import by.loiko.library.dao.AuthorDAO;
import by.loiko.library.dao.BookDAO;
import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.GenreDAO;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
import by.loiko.library.receiver.ReceiverException;
import by.loiko.library.receiver.BookReceiver;
import by.loiko.library.validator.EntityValidator;
import by.loiko.library.validator.FieldEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class BookReceiverImpl implements BookReceiver {
    private static Logger logger = LogManager.getLogger();
    private BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
    private GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findBookByTitle(java.lang.String)
     */
    @Override
    public List<Book> findBookByTitle(String title) throws ReceiverException {
        if (title == null) {
            throw new ReceiverException("Title of book is empty");
        }

        List<Book> booksList;
        try {
            booksList = bookDAO.findBooksByTitle(title);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBooksByTitle command wasn't executed", e);
        }

        if (booksList.isEmpty()) {
            throw new ReceiverException("Books with this title wasn't found");
        }

        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findBooksByArrayOfId(java.lang.String[])
     */
    @Override
    public List<Book> findBooksByArrayOfId(String[] idTextArray) throws ReceiverException {
        if (idTextArray == null){
            throw new ReceiverException("Wrong parameters");
        }
        EntityValidator entityValidator = new EntityValidator();
        List<Long> idList = entityValidator.validateArrayOfId(idTextArray);
        if (idList.isEmpty()) {
            throw new ReceiverException("Wrong parameters");
        }

        List<Book> bookList;
        try {
            bookList = bookDAO.findEntitiesByArrayOfId(idList);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBooksByArrayOfId command wasn't executed", e);
        }

        if (bookList.isEmpty()) {
            throw new ReceiverException("Books wasn't found");
        }

        return bookList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findBooksByGenreId(java.lang.String)
     */
    @Override
    public List<Book> findBooksByGenreId(String id) throws ReceiverException {
        long genreId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        List<Book> bookList;
        try {
            bookList = bookDAO.findBooksByGenreId(genreId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBooksByGenreId command wasn't executed", e);
        }

        if (bookList.isEmpty()) {
            throw new ReceiverException("Books wasn't found");
        }

        return bookList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findBooksByAuthorId(java.lang.String)
     */
    @Override
    public List<Book> findBooksByAuthorId(String id) throws ReceiverException {
        long authorId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            authorId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        List<Book> bookList;
        try {
            bookList = bookDAO.findBooksByAuthorId(authorId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBooksByAuthorId command wasn't executed", e);
        }

        if (bookList.isEmpty()) {
            throw new ReceiverException("Books wasn't found");
        }

        return bookList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllBooksAbs()
     */
    @Override
    public List<Book> findAllBooksAbs() throws ReceiverException {
        List<Book> bookList;

        try {
            bookList = bookDAO.findAllEntities();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllBooks command wasn't executed", e);
        }

        return bookList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findBookById(java.lang.String)
     */
    @Override
    public Book findBookById(String id) throws ReceiverException {
        long bookId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            bookId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Book book;
        try {
            List<Genre> genreList = genreDAO.findGenresByBookId(bookId);
            List<Author> authorList = authorDAO.findAuthorsByBookId(bookId);
            book = bookDAO.findEntityById(bookId);

            if (book == null) {
                throw new ReceiverException("Book with this id was not found");
            }
            book.setGenres(genreList);
            book.setAuthors(authorList);
        } catch (DAOException | NullPointerException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBookById command wasn't executed", e);
        }

        return book;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllGenres()
     */
    @Override
    public List<Genre> findAllGenres() throws ReceiverException {
        List<Genre> genreList;
        try {
            genreList = genreDAO.findAllEntities();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllGenres command wasn't executed", e);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllGenresAbs()
     */
    @Override
    public List<Genre> findAllGenresAbs() throws ReceiverException {
        List<Genre> genreList;
        try {
            genreList = genreDAO.findAllGenresAbs();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllGenres command wasn't executed", e);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllAuthors()
     */
    @Override
    public List<Author> findAllAuthors() throws ReceiverException {
        List<Author> authorList;
        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            authorList = authorDAO.findAllEntities();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllAuthors command wasn't executed", e);
        }

        return authorList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllAuthorsAbs()
     */
    @Override
    public List<Author> findAllAuthorsAbs() throws ReceiverException {
        List<Author> authorList;
        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            authorList = authorDAO.findAllAuthorsAbs();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllAuthors command wasn't executed", e);
        }

        return authorList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllNotEmptyGenres()
     */
    @Override
    public List<Genre> findAllNotEmptyGenres() throws ReceiverException {
        List<Genre> genreList;
        try {
            genreList = genreDAO.findAllNotEmptyGenres();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllNotEmptyGenres command wasn't executed", e);
        }
        return genreList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllNotEmptyAuthors()
     */
    @Override
    public List<Author> findAllNotEmptyAuthors() throws ReceiverException {
        List<Author> authorList;
        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            authorList = authorDAO.findAllNotEmptyAuthors();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllNotEmptyAuthors command wasn't executed", e);
        }

        return authorList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAllBooks()
     */
    @Override
    public List<Book> findAllBooks() throws ReceiverException {
        List<Book> bookList;
        try {
            bookList = bookDAO.findAllBooks();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllBooks command wasn't executed", e);
        }

        return bookList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findGenreById(java.lang.String)
     */
    @Override
    public Genre findGenreById(String id) throws ReceiverException {
        long genreId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Genre genre;
        try {
            genre = genreDAO.findEntityById(genreId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findGenreById command wasn't executed", e);
        }

        if (genre == null) {
            throw new ReceiverException("Genre wasn't found");
        }

        return genre;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#findAuthorById(java.lang.String)
     */
    @Override
    public Author findAuthorById(String id) throws ReceiverException {
        long authorId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            authorId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        Author author;
        try {
            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
            author = authorDAO.findEntityById(authorId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAuthorById command wasn't executed: ", e);
        }

        if (author == null) {
            throw new ReceiverException("Author wasn't found");
        }

        return author;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#addNewBook(java.util.Map, java.lang.String[], java.lang.String[])
     */
    @Override
    public Map<String, String> addNewBook(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateBook(paramsMap, genres, authors);

        if (errorMap.isEmpty()) {
            try {
                bookDAO.addNewEntity(buildBook(paramsMap, genres, authors));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("AddBook command wasn't executed", e);
            }
        }
        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#addNewGenre(java.util.Map)
     */
    @Override
    public Map<String, String> addNewGenre(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                genreDAO.addNewEntity(buildGenre(paramsMap));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("AddGenre command wasn't executed", e);
            }
        }

        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#addNewAuthor(java.util.Map)
     */
    @Override
    public Map<String, String> addNewAuthor(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
                authorDAO.addNewEntity(buildAuthor(paramsMap));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("AddAuthor command wasn't executed", e);
            }
        }

        return errorMap;
    }


    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#deleteGenre(java.lang.String)
     */
    @Override
    public void deleteGenre(String id) throws ReceiverException {
        long genreId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            genreDAO.deleteEntityById(genreId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("deleteGenre command wasn't executed", e);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#deleteAuthor(java.lang.String)
     */
    @Override
    public void deleteAuthor(String id) throws ReceiverException {
        long authorId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            authorId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            authorDAO.deleteEntityById(authorId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("deleteAuthor command wasn't executed", e);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#deleteBook(java.lang.String)
     */
    @Override
    public void deleteBook(String id) throws ReceiverException {
        long bookId;
        EntityValidator entityValidator = new EntityValidator();
        if (entityValidator.validateId(id)) {
            bookId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            bookDAO.deleteEntityById(bookId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("deleteBook command wasn't executed", e);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#updateGenreInfo(java.util.Map)
     */
    @Override
    public Map<String, String> updateGenreInfo(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                genreDAO.updateEntity(buildGenre(paramsMap));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("UpdateGenre command wasn't executed", e);
            }
        }

        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#updateAuthorInfo(java.util.Map)
     */
    @Override
    public Map<String, String> updateAuthorInfo(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                authorDAO.updateEntity(buildAuthor(paramsMap));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("UpdateAuthor command wasn't executed", e);
            }
        }

        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.BookReceiver#updateBookInfo(java.util.Map, java.lang.String[], java.lang.String[])
     */
    @Override
    public Map<String, String> updateBookInfo(Map<String, String> paramsMap, String[] genres, String[] authors) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateBook(paramsMap, genres, authors);

        if (errorMap.isEmpty()) {

            try {
                bookDAO.updateEntity(buildBook(paramsMap, genres, authors));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("UpdateBook command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    /**
     * Builds the genre.
     *
     * @param paramsMap the params map
     * @return the genre
     */
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

    /**
     * Builds the author.
     *
     * @param paramsMap the params map
     * @return the author
     */
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

    /**
     * Builds the book.
     *
     * @param paramsMap the params map
     * @param genres the genres
     * @param authors the authors
     * @return the book
     */
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
