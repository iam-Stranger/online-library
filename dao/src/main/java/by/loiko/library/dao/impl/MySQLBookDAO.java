package by.loiko.library.dao.impl;

import by.loiko.library.dao.BookDAO;
import by.loiko.library.entity.Book;
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
public class MySQLBookDAO implements BookDAO {
    //private static Logger logger = LogManager.getLogger();

    private static final String FIND_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ? AND deleted = 0";
    private static final String FIND_ALL_BOOKS_READER = "SELECT * FROM book WHERE deleted = 0";
    private static final String FIND_ALL_BOOKS_LIBRARIAN = "SELECT * FROM book";
    private static final String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String FIND_BOOKS_BY_ARRAY_OF_ID = "SELECT * FROM book WHERE deleted = 0 AND real_amount > 0";
    private final static String ADD_NEW_BOOK = "INSERT INTO book (title, publish_year, total_amount, real_amount) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_BOOK = "UPDATE book SET title = ? WHERE id = ?";
    private final static String DELETE_BOOK = "UPDATE book SET deleted = '1' WHERE id = ?";

    @Override
    public List<Book> findAllEntities() throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        Statement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS_LIBRARIAN);

            while (resultSet.next()) {
                booksList.add(buildBook(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAllEntities(Book) method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }

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
                book = buildBook(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findEntityById method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return book;
    }

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
            throw new DAOException("Error in delBook method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

    @Override
    public void addNewEntity(Book entity) throws DAOException {

    }

    @Override
    public void updateEntity(Book entity) throws DAOException {

    }

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
                booksList.add(buildBook(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findBooksByTitle method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
        return booksList;
    }

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
                booksList.add(buildBook(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error in findBooksByArrayOfId method: ", e);

        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return booksList;
    }


    private Book buildBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setPublishYear(resultSet.getInt("publish_year"));
        book.setTotalAmount(resultSet.getInt("total_amount"));
        book.setRealAmount(resultSet.getInt("real_amount"));
        book.setIsDeleted(resultSet.getBoolean("deleted"));

        return book;
    }
}
