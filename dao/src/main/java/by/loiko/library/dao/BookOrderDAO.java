package by.loiko.library.dao;

import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface BookOrderDAO extends AbstractDAO<BookOrder> {

    List<BookOrder> findAllBookOrders() throws DAOException;

    List<BookOrder> findBookOrdersByUserId(long userId) throws DAOException;

    void changeBookOrderStatusToIssued(long id, int orderTypeId, String dateFrom) throws DAOException;

    void changeBookOrderStatusToCanceled(long id) throws DAOException;

    void changeBookOrderStatusToReturned(long id, String dateReturn) throws DAOException;

    int findCountCurrentBooksByUserId(long id) throws DAOException;

    int findCountExpiredBooksByUserIdAndDate(long id, String date) throws DAOException;

    void createNewOrderWithStatusOrdered(long userId, long bookId, String date, int orderType) throws DAOException;

}
