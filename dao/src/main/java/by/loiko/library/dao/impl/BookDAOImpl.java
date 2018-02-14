package by.loiko.library.dao.impl;

import by.loiko.library.creator.BookCreator;
import by.loiko.library.dao.BookDAO;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class BookDAOImpl implements BookDAO {
    /* ResultSetCreator for Book objects */
    private BookCreator bookCreator = new BookCreator();

    private final static String FIND_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ? AND deleted = 0 ORDER BY title";
    /* MySQL query find all Books which not deleted  */
    private final static String FIND_ALL_BOOKS = "SELECT * FROM book WHERE deleted = 0 ORDER BY title";
    /* MySQL query find ALL Books (and deleted) */
    private final static String FIND_ALL_BOOKS_ABS = "SELECT * FROM book ORDER BY title";
    private final static String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    /* MySQL query find Books of array books IDs */
    private final static String FIND_BOOKS_BY_ARRAY_OF_ID = "SELECT * FROM book WHERE deleted = 0 AND real_amount > 0";
    /* MySQL query change status Book to deleted */
    private final static String DELETE_BOOK = "UPDATE book SET deleted = '1' WHERE id = ?";
    private final static String ADD_NEW_BOOK = "INSERT INTO book (title, publish_year, total_amount, real_amount) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_BOOK = "UPDATE book SET title = ?, publish_year = ?, total_amount = ?, real_amount = ?, deleted = ? WHERE id = ?";
    /* MySQL query delete Genres ID which belong Book in book_genres table */
    private final static String DELETE_GENRES_OF_BOOK = "DELETE FROM book_genres WHERE book_id = ?";
    /* MySQL query insert Genres ID which belong Book in book_genres table */
    private final static String INSERT_GENRES_OF_BOOK = "INSERT INTO book_genres (book_id, genre_id) VALUES (?, ?)";
    /* MySQL query delete Authors ID which belong Book in book_genres table */
    private final static String DELETE_AUTHORS_OF_BOOK = "DELETE FROM book_authors WHERE book_id = ?";
    /* MySQL query insert Authors ID which belong Book in book_genres table */
    private final static String INSERT_AUTHORS_OF_BOOK = "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)";
    private final static String FIND_BOOKS_BY_GENRE_ID = "SELECT * FROM book_genres INNER JOIN book WHERE book_id = id AND deleted = 0 AND genre_id = ? ORDER BY book.title";
    private final static String FIND_BOOKS_BY_AUTHOR_ID = "SELECT * FROM book_authors INNER JOIN book WHERE book_id = id AND deleted = 0 AND author_id = ? ORDER BY book.title";

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findAllEntities()
     */
    @Override
    public List<Book> findAllEntities() throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS_ABS);

            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAllEntities(Book) method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntityById(long)
     */
    @Override
    public Book findEntityById(long id) throws DAOException {
        Book book = null;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_BOOK_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = bookCreator.createBook(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findEntityById method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return book;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#deleteEntityById(long)
     */
    @Override
    public void deleteEntityById(long id) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(DELETE_BOOK);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delBook method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#addNewEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void addNewEntity(Book book) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);

            statement = proxyConnection.prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPublishYear());
            statement.setInt(3, book.getTotalAmount());
            statement.setInt(4, book.getRealAmount());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                book.setId(resultSet.getLong(1));
            }

            for (Genre genre : book.getGenres()) {
                statement = proxyConnection.prepareStatement(INSERT_GENRES_OF_BOOK);
                statement.setLong(1, book.getId());
                statement.setLong(2, genre.getId());
                statement.executeUpdate();
            }

            for (Author author : book.getAuthors()) {
                statement = proxyConnection.prepareStatement(INSERT_AUTHORS_OF_BOOK);
                statement.setLong(1, book.getId());
                statement.setLong(2, author.getId());
                statement.executeUpdate();
            }

            proxyConnection.commit();
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Error in addBook method: " + e.getMessage(), e);
            }
            throw new DAOException("Error in addBook method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#updateEntity(by.loiko.library.entity.Entity)
     */
    @Override
    public void updateEntity(Book book) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);

            statement = proxyConnection.prepareStatement(UPDATE_BOOK);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPublishYear());
            statement.setInt(3, book.getTotalAmount());
            statement.setInt(4, book.getRealAmount());
            statement.setBoolean(5, book.getIsDeleted());
            statement.setLong(6, book.getId());
            statement.executeUpdate();

            statement = proxyConnection.prepareStatement(DELETE_GENRES_OF_BOOK);
            statement.setLong(1, book.getId());
            statement.executeUpdate();

            for (Genre genre : book.getGenres()) {
                statement = proxyConnection.prepareStatement(INSERT_GENRES_OF_BOOK);
                statement.setLong(1, book.getId());
                statement.setLong(2, genre.getId());
                statement.executeUpdate();
            }

            statement = proxyConnection.prepareStatement(DELETE_AUTHORS_OF_BOOK);
            statement.setLong(1, book.getId());
            statement.executeUpdate();

            for (Author author : book.getAuthors()) {
                statement = proxyConnection.prepareStatement(INSERT_AUTHORS_OF_BOOK);
                statement.setLong(1, book.getId());
                statement.setLong(2, author.getId());
                statement.executeUpdate();
            }

            proxyConnection.commit();
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Error in editBook method: " + e.getMessage(), e);
            }
            throw new DAOException("Error in editBook method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.BookDAO#findBooksByTitle(java.lang.String)
     */
    @Override
    public ArrayList<Book> findBooksByTitle(String title) throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_BOOKS_BY_TITLE);
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findBooksByTitle method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.BookDAO#findAllBooks()
     */
    @Override
    public ArrayList<Book> findAllBooks() throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS);

            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAllBooks method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.BookDAO#findBooksByGenreId(long)
     */
    @Override
    public List<Book> findBooksByGenreId(long genreId) throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_BOOKS_BY_GENRE_ID);
            statement.setLong(1, genreId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findBooksByGenreId method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.BookDAO#findBooksByAuthorId(long)
     */
    @Override
    public List<Book> findBooksByAuthorId(long authorId) throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_BOOKS_BY_AUTHOR_ID);
            statement.setLong(1, authorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findBooksByGenreId method: " + e.getMessage(), e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.dao.AbstractDAO#findEntitiesByArrayOfId(java.util.List)
     */
    @Override
    public List<Book> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        List<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();

            String wildcards = createINExpression(idList);
            statement = proxyConnection.prepareStatement(FIND_BOOKS_BY_ARRAY_OF_ID + wildcards);
            for (int i = 0; i < idList.size(); i++) {
                statement.setLong(i + 1, idList.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                booksList.add(bookCreator.createBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error in findBooksByArrayOfId method: " + e.getMessage(), e);

        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }
}
