package by.loiko.library.creator;

import by.loiko.library.entity.Book;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/***
 Author: Aliaksei Loika
 Date: 07.02.2018
 ***/
public class BookOrderCreator implements ResultSetCreator {
    private static Logger logger = LogManager.getLogger();

    private static final String BOOK_ORDER_ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String TITLE = "title";
    private static final String BOOK_ID = "book_id";
    private static final String DATE_FROM = "date_from";
    private static final String DATE_TO = "date_to";
    private static final String DATE_RETURN = "date_return";
    private static final String ORDER_TYPE_ID = "order_type_id";
    private static final String STATUS_ID = "status_id";


    /**
     * Creates the book order.
     *
     * @param resultSet the result set
     * @return the book order
     * @throws DAOException the DAO exception
     */
    public BookOrder createBookOrder(ResultSet resultSet) throws DAOException {
        List<String> columnList = null;
        BookOrder bookOrder = new BookOrder();
        User user = new User();
        Book book = new Book();

        bookOrder.setBook(book);
        bookOrder.setUser(user);

        try {
            columnList = getColumnsNames(resultSet);

            for (String column : columnList) {

                switch (column) {
                    case USER_ID:
                        user.setId(resultSet.getLong(USER_ID));
                        break;
                    case LOGIN:
                        user.setLogin(resultSet.getString(LOGIN));
                        break;
                    case FIRST_NAME:
                        user.setFirstName(resultSet.getString(FIRST_NAME));
                        break;
                    case LAST_NAME:
                        user.setLastName(resultSet.getString(LAST_NAME));
                        break;
                    case BOOK_ID:
                        book.setId(resultSet.getLong(BOOK_ID));
                        break;
                    case TITLE:
                        book.setTitle(resultSet.getString(TITLE));
                        break;
                    case BOOK_ORDER_ID:
                        bookOrder.setId(resultSet.getLong(BOOK_ORDER_ID));
                        break;
                    case DATE_FROM:
                        bookOrder.setDateFrom(resultSet.getDate(DATE_FROM).toLocalDate());
                        break;
                    case DATE_TO:
                        if (resultSet.getDate(DATE_TO) != null) {
                            bookOrder.setDateTo(resultSet.getDate(DATE_TO).toLocalDate());
                        }
                        break;
                    case DATE_RETURN:
                        if (resultSet.getDate(DATE_RETURN) != null) {
                            bookOrder.setDateReturn(resultSet.getDate(DATE_RETURN).toLocalDate());
                        }
                        break;
                    case ORDER_TYPE_ID:
                        bookOrder.setOrderTypeId(resultSet.getInt(ORDER_TYPE_ID));
                        break;
                    case STATUS_ID:
                        bookOrder.setStatusId(resultSet.getInt(STATUS_ID));
                        break;
                    default:
                        logger.log(Level.DEBUG, column);
                }

            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("Error in createBookOrder method", e);
        }

        return bookOrder;
    }

}
