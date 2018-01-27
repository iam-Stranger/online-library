package by.loiko.library.dao.impl;

import by.loiko.library.dao.BookOrderDAO;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.entity.Entity;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class MySQLBookOrderDAO implements BookOrderDAO {
    private static final String FIND_ALL_BOOK_ORDERS = "SELECT bo.id , u.id AS user_id, b.id AS book_id, b.title,  u.login, bo.date_from, bo.date_to, bo.date_return, bo.order_type_id, bo.status_id  " +
            "FROM book_orders bo INNER JOIN user u INNER JOIN book b ON bo.user_id = u.id AND bo.book_id = b.id";

    @Override
    public ArrayList findAll() throws DAOException {
        ArrayList<BookOrder> ordersList = new ArrayList<>();
        ProxyConnection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOK_ORDERS);

            while (resultSet.next()) {
                ordersList.add(buildBookOrder(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAll(BookOrder) method: ", e);
        } finally {
            close(statement);
            releaseConnection(connection);
        }


        return ordersList;
    }

    @Override
    public Entity findEntityById(long id) throws DAOException {
        return null;
    }

    @Override
    public void deleteEntityById(long id) throws DAOException {

    }

    @Override
    public ArrayList findEntitiesByArrayOfId(ArrayList idList) throws DAOException {
        return null;
    }

    private BookOrder buildBookOrder(ResultSet resultSet) throws SQLException {
        BookOrder bookOrder = new BookOrder();
        User user = new User();
        Book book = new Book();

        user.setId(resultSet.getLong("user_id"));
        user.setLogin(resultSet.getString("login"));
        book.setId(resultSet.getLong("book_id"));
        book.setTitle(resultSet.getString("title"));

        bookOrder.setId(resultSet.getLong("id"));
        bookOrder.setBook(book);
        bookOrder.setUser(user);

        if (resultSet.getDate("date_from") != null) {
            bookOrder.setDateFrom(resultSet.getDate("date_from").toLocalDate());
        }
        if (resultSet.getDate("date_to") != null) {
            bookOrder.setDateTo(resultSet.getDate("date_to").toLocalDate());
        }
        if (resultSet.getDate("date_return") != null) {
            bookOrder.setDateReturn(resultSet.getDate("date_return").toLocalDate());
        }
        bookOrder.setOrderTypeId(resultSet.getInt("order_type_id"));
        bookOrder.setStatusId(resultSet.getInt("status_id"));

        return bookOrder;
    }
}
