package by.loiko.library.dao.impl;

import by.loiko.library.dao.BookDAO;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class MySQLBookDAO implements BookDAO {
    private static Logger logger = LogManager.getLogger();

    private static final String FIND_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ? AND deleted = 0";
    private static final String FIND_ALL_BOOKS = "SELECT * FROM book WHERE deleted = 0";
    private static final String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String FIND_BOOKS_BY_ARRAY_OF_ID = "SELECT * FROM book WHERE deleted = 0 AND real_amount > 0";

    // AND id IN
    @Override
    public ArrayList<Book> findAll() throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_ALL_BOOKS);


        } catch (SQLException e) {
            throw new DAOException("Error in findAll(Book) method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

        return null;
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
    public ArrayList<Book> findBooksByArrayOfId(ArrayList<Long> idList) throws DAOException {
        ArrayList<Book> booksList = new ArrayList<>();
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();

            String wildcards = createParamExpression(idList);
            statement = proxyConnection.prepareStatement(FIND_BOOKS_BY_ARRAY_OF_ID + wildcards);

            System.out.println(FIND_BOOKS_BY_ARRAY_OF_ID + wildcards);
            for (int i = 0; i < idList.size(); i++) {
                statement.setLong(i + 1, idList.get(i));
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // System.out.println(buildBook(resultSet));
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

    @Override
    public void addNewBook(String title, int publishYear, int amount) {

    }


    private Book buildBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setPublishYear(resultSet.getInt("publish_year"));
        book.setTotalAmount(resultSet.getInt("total_amount"));
        book.setRealAmount(resultSet.getInt("real_amount"));
        book.setDeleted(resultSet.getBoolean("deleted"));

        return book;
    }

    private String createParamExpression(ArrayList<Long> idList) {

        StringBuffer expression = new StringBuffer().append(" AND id IN (");
        for (int i = 0; i < idList.size() - 1; i++) {
            expression.append("?, ");
        }
        expression.append("?)");

        return expression.toString();
    }
}
