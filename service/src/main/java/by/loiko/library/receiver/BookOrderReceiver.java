package by.loiko.library.receiver;

import by.loiko.library.entity.BookOrder;
import by.loiko.library.entity.User;

import java.util.List;


/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface BookOrderReceiver extends AbstractReceiver {

    /**
     * Find all book orders (and deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<BookOrder> findAllBookOrdersAbs() throws ReceiverException;
    
    /**
     * Find all book orders (not deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<BookOrder> findAllBookOrders() throws ReceiverException;
    
    /**
     * Find book orders by user.
     *
     * @param user the user
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<BookOrder> findBookOrdersByUser(User user) throws ReceiverException;

    /**
     * Change book order status to ISSUED.
     *
     * @param id the id
     * @param orderType the order type
     * @param date the date
     * @throws ReceiverException the receiver exception
     */
    void changeBookOrderStatusToIssued(String id, String orderType, String date) throws ReceiverException;
    
    /**
     * Change book order status to CANCELED.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void changeBookOrderStatusToCanceled(String id) throws ReceiverException;
    
    /**
     * Change book order status to RETURNED.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void changeBookOrderStatusToReturned(String id) throws ReceiverException;

    /**
     * Find count possible books by user.
     *
     * @param user the user
     * @return the int
     * @throws ReceiverException the receiver exception
     */
    int findCountPossibleBooksByUser(User user) throws ReceiverException;
    
    /**
     * Check no debt books by user.
     *
     * @param user the user
     * @return true, if successful
     * @throws ReceiverException the receiver exception
     */
    boolean checkNoDebtBooksByUser(User user) throws ReceiverException;

    /**
     * Creates the new user orders.
     *
     * @param user the user
     * @param orderTypes the order types
     * @param booksId the books id
     * @throws ReceiverException the receiver exception
     */
    void createNewUserOrders(User user, String[] orderTypes, String[] booksId) throws ReceiverException;

}
