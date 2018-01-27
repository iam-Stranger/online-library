package by.loiko.library.receiver;

import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.ReceiverException;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public interface BookOrderReceiver extends AbstractReceiver {

    ArrayList<BookOrder> findAllBookOrders() throws ReceiverException;

}
