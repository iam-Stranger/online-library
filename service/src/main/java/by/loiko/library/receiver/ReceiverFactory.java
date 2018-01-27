package by.loiko.library.receiver;

import by.loiko.library.receiver.impl.BookOrderReceiverImpl;
import by.loiko.library.receiver.impl.BookReceiverImpl;
import by.loiko.library.receiver.impl.UserReceiverImpl;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class ReceiverFactory {

    private static final ReceiverFactory instance = new ReceiverFactory();

    private final BookReceiver bookReceiver = new BookReceiverImpl();
    private final UserReceiver userReceiver = new UserReceiverImpl();
    private final BookOrderReceiver bookOrderReceiver = new BookOrderReceiverImpl();

    private ReceiverFactory() {
    }

    public static ReceiverFactory getInstance() {
        return instance;
    }

    public BookReceiver getBookReceiver() {
        return bookReceiver;
    }

    public UserReceiver getUserReceiver() {
        return userReceiver;
    }

    public BookOrderReceiver getBookOrderReceiver() {
        return bookOrderReceiver;
    }
}
