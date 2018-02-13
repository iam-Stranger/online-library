package by.loiko.library.receiver;

import by.loiko.library.entity.BookOrder;
import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface BookOrderReceiver extends AbstractReceiver {

    List<BookOrder> findAllBookOrdersAbs() throws ReceiverException;
    List<BookOrder> findAllBookOrders() throws ReceiverException;
    List<BookOrder> findBookOrdersByUser(User user) throws ReceiverException;

    void changeBookOrderStatusToIssued(String id, String orderType, String date) throws ReceiverException;
    void changeBookOrderStatusToCanceled(String id) throws ReceiverException;
    void changeBookOrderStatusToReturned(String id) throws ReceiverException;

    int findCountPossibleBooksByUser(User user) throws ReceiverException;
    boolean checkNoDebtBooksByUser(User user) throws ReceiverException;

    void createNewUserOrders(User user, String[] orderTypes, String[] booksId) throws ReceiverException;

}
