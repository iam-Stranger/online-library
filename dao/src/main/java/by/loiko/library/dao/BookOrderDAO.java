package by.loiko.library.dao;

import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface BookOrderDAO extends AbstractDAO<BookOrder> {

    /**
     * Find all book orders which not CANCELED or RETURNED
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<BookOrder> findAllBookOrders() throws DAOException;

    /**
     * Find book orders by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<BookOrder> findBookOrdersByUserId(long userId) throws DAOException;

    /**
     * Change book order status to ISSUED.
     *
     * @param id the id
     * @param orderTypeId the order type id
     * @param dateFrom the date from
     * @throws DAOException the DAO exception
     */
    void changeBookOrderStatusToIssued(long id, int orderTypeId, String dateFrom) throws DAOException;

    /**
     * Change book order status to CANCELED.
     *
     * @param id the id
     * @throws DAOException the DAO exception
     */
    void changeBookOrderStatusToCanceled(long id) throws DAOException;

    /**
     * Change book order status to RETURNED.
     *
     * @param id the id
     * @param dateReturn the date return
     * @throws DAOException the DAO exception
     */
    void changeBookOrderStatusToReturned(long id, String dateReturn) throws DAOException;

    /**
     * Find count current books by user id.
     *
     * @param id the id
     * @return the int
     * @throws DAOException the DAO exception
     */
    int findCountCurrentBooksByUserId(long id) throws DAOException;

    /**
     * Find count expired books by user id and date.
     *
     * @param id the id
     * @param date the date
     * @return the int
     * @throws DAOException the DAO exception
     */
    int findCountExpiredBooksByUserIdAndDate(long id, String date) throws DAOException;

    /**
     * Creates the new order with status ORDERED.
     *
     * @param userId the user id
     * @param bookId the book id
     * @param date the date
     * @param orderType the order type
     * @throws DAOException the DAO exception
     */
    void createNewOrderWithStatusOrdered(long userId, long bookId, String date, int orderType) throws DAOException;

}
