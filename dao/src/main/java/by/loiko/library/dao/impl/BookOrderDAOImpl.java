package by.loiko.library.dao.impl;

import by.loiko.library.creator.BookOrderCreator;
import by.loiko.library.dao.BookOrderDAO;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class BookOrderDAOImpl implements BookOrderDAO {
    private static Logger logger = LogManager.getLogger();

    private static final String FIND_ALL_BOOK_ORDERS_ABS = "SELECT bo.id , u.id AS user_id, b.id AS book_id, b.title,  u.login, bo.date_from, bo.date_to, bo.date_return, bo.order_type_id, bo.status_id  " +
            "FROM book_orders bo INNER JOIN user u INNER JOIN book b ON bo.user_id = u.id AND bo.book_id = b.id";
    private static final String FIND_ALL_BOOK_ORDERS = "SELECT bo.id, u.id AS user_id, b.id AS book_id, b.title,  u.firstname, u.lastname, bo.date_from, bo.date_to, bo.order_type_id, bo.status_id  " +
            "FROM book_orders bo INNER JOIN user u INNER JOIN book b ON bo.user_id = u.id AND bo.book_id = b.id WHERE status_id < 3";
    private static final String FIND_BOOK_ORDERS_BY_USER_ID = "SELECT bo.id, u.id AS user_id, b.id AS book_id, b.title, bo.date_from, bo.date_to, bo.order_type_id, bo.status_id " +
            "FROM book_orders bo INNER JOIN user u INNER JOIN book b ON bo.user_id = u.id AND bo.book_id = b.id WHERE user_id = ? AND status_id < 3";

    private static final String CHANGE_BOOK_ORDER_STATUS_TO_ISSUED = "UPDATE book_orders SET date_to = ?,  order_type_id = ?, status_id = 2 WHERE id = ?";
    private static final String CHANGE_BOOK_ORDER_STATUS_TO_CANCELED = "UPDATE book_orders SET status_id = 3 WHERE id = ?";
    private static final String CHANGE_BOOK_ORDER_STATUS_TO_RETURNED = "UPDATE book_orders SET status_id = 4, date_return = ? WHERE id = ?";
    private static final String FIND_BOOK_ID_BY_ORDER_ID = "SELECT book_id FROM book_orders WHERE id = ?";
    private static final String BOOK_REAL_AMOUNT_INCREASE_BY_ID = "UPDATE book SET real_amount = real_amount + 1 WHERE id = ?";
    private static final String BOOK_REAL_AMOUNT_REDUCE_BY_ID = "UPDATE book SET real_amount = real_amount - 1 WHERE id = ?";

    private static final String FIND_COUNT_CURRENT_BOOKS_BY_USER_ID = "SELECT count(*) FROM book_orders WHERE user_id = ? AND status_id < 3";
    private static final String FIND_COUNT_EXPIRED_BOOKS_BY_USER_ID_DATE = "SELECT count(*) FROM book_orders WHERE status_id < 3 AND date_to < ? AND user_id = ?";

    private static final String ADD_NEW_ORDER_WITH_STATUS_ORDERED = "INSERT INTO book_orders (user_id, book_id, date_from, order_type_id, status_id) VALUES (?, ?, ?, ?, 1)";

    @Override
    public List<BookOrder> findAllEntities() throws DAOException {
        List<BookOrder> ordersList = new ArrayList<>();
        ProxyConnection proxyconnection = null;
        Statement statement = null;

        BookOrderCreator bookOrderCreator = new BookOrderCreator();

        try {
            proxyconnection = ConnectionPool.getInstance().getConnection();
            statement = proxyconnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOK_ORDERS_ABS);

            while (resultSet.next()) {
                ordersList.add(bookOrderCreator.createBookOrder(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAllEntities(BookOrder) method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyconnection);
        }


        return ordersList;
    }

    @Override
    public List<BookOrder> findAllBookOrders() throws DAOException {
        List<BookOrder> ordersList = new ArrayList<>();
        ProxyConnection proxyconnection = null;
        Statement statement = null;

        BookOrderCreator bookOrderCreator = new BookOrderCreator();

        try {
            proxyconnection = ConnectionPool.getInstance().getConnection();
            statement = proxyconnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOK_ORDERS);

            while (resultSet.next()) {
                ordersList.add(bookOrderCreator.createBookOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in findAllBookOrders method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyconnection);
        }

        return ordersList;
    }

    @Override
    public List<BookOrder> findBookOrdersByUserId(long userId) throws DAOException {
        List<BookOrder> ordersList = new ArrayList<>();
        ProxyConnection proxyconnection = null;
        PreparedStatement statement = null;

        BookOrderCreator bookOrderCreator = new BookOrderCreator();

        try {
            proxyconnection = ConnectionPool.getInstance().getConnection();
            statement = proxyconnection.prepareStatement(FIND_BOOK_ORDERS_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(bookOrderCreator.createBookOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in findAllBookOrders method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyconnection);
        }

        return ordersList;
    }

    @Override
    public void changeBookOrderStatusToIssued(long id, int orderTypeId, String dateFrom) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(CHANGE_BOOK_ORDER_STATUS_TO_ISSUED);
            statement.setString(1, dateFrom);
            statement.setInt(2, orderTypeId);
            statement.setLong(3, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in changeBookOrderStatusToIssued method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }


    }

    @Override
    public void changeBookOrderStatusToCanceled(long id) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);

            statement = proxyConnection.prepareStatement(CHANGE_BOOK_ORDER_STATUS_TO_CANCELED);
            statement.setLong(1, id);
            statement.executeUpdate();

            statement = proxyConnection.prepareStatement(FIND_BOOK_ID_BY_ORDER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            long bookId = 0;
            if (resultSet.next()) {
                bookId = resultSet.getLong(1);
            }

            statement = proxyConnection.prepareStatement(BOOK_REAL_AMOUNT_INCREASE_BY_ID);
            statement.setLong(1, bookId);
            statement.executeUpdate();

            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e1) {
                throw new DAOException("Error in changeBookOrderStatusToCanceled method: ", e);
            }
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in changeBookOrderStatusToCanceled method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

    @Override
    public void changeBookOrderStatusToReturned(long id, String dateReturn) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);

            statement = proxyConnection.prepareStatement(CHANGE_BOOK_ORDER_STATUS_TO_RETURNED);
            statement.setString(1, dateReturn);
            statement.setLong(2, id);
            statement.executeUpdate();

            statement = proxyConnection.prepareStatement(FIND_BOOK_ID_BY_ORDER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            long bookId = 0;
            if (resultSet.next()) {
                bookId = resultSet.getLong(1);
            }

            statement = proxyConnection.prepareStatement(BOOK_REAL_AMOUNT_INCREASE_BY_ID);
            statement.setLong(1, bookId);
            statement.executeUpdate();

            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e1) {
                throw new DAOException("Error in changeBookOrderStatusToReturned method: ", e);
            }
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in changeBookOrderStatusToReturned method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }

    }

    @Override
    public int findCountCurrentBooksByUserId(long id) throws DAOException {
        int count = 0;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_COUNT_CURRENT_BOOKS_BY_USER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in findCountCurrentBooksByUserId method: ", e);
        }

        return count;
    }

    @Override
    public int findCountExpiredBooksByUserIdAndDate(long id, String date) throws DAOException {
        int count = 0;
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(FIND_COUNT_EXPIRED_BOOKS_BY_USER_ID_DATE);
            statement.setString(1, date);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in findCountExpiredBooksByUserIdAndDate method: ", e);
        }

        return count;
    }

    @Override
    public void createNewOrderWithStatusOrdered(long userId, long bookId, String date, int orderType) throws DAOException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            proxyConnection.setAutoCommit(false);

            statement = proxyConnection.prepareStatement(ADD_NEW_ORDER_WITH_STATUS_ORDERED);
            statement.setLong(1, userId);
            statement.setLong(2, bookId);
            statement.setString(3, date);
            statement.setInt(4, orderType);
            statement.executeUpdate();

            statement = proxyConnection.prepareStatement(BOOK_REAL_AMOUNT_REDUCE_BY_ID);
            statement.setLong(1, bookId);
            statement.executeUpdate();

            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e1) {
                throw new DAOException("Error in createNewOrderWithStatusOrdered method: ", e);
            }
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createNewOrderWithStatusOrdered method: ", e);
        } finally {
            close(statement);
            releaseConnection(proxyConnection);
        }
    }

    @Override
    public BookOrder findEntityById(long id) throws DAOException {
        return null;
    }

    @Override
    public void deleteEntityById(long id) throws DAOException {

    }

    @Override
    public void addNewEntity(BookOrder entity) throws DAOException {

    }

    @Override
    public void updateEntity(BookOrder entity) throws DAOException {

    }

    @Override
    public List<BookOrder> findEntitiesByArrayOfId(List<Long> idList) throws DAOException {
        return null;
    }

}
