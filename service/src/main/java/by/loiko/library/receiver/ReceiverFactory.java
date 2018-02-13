package by.loiko.library.receiver;

import by.loiko.library.receiver.impl.BookOrderReceiverImpl;
import by.loiko.library.receiver.impl.BookReceiverImpl;
import by.loiko.library.receiver.impl.UserReceiverImpl;


/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class ReceiverFactory {

    /** The Constant instance. */
    private static final ReceiverFactory instance = new ReceiverFactory();

    /** The book receiver. */
    private final BookReceiver bookReceiver = new BookReceiverImpl();
    
    /** The user receiver. */
    private final UserReceiver userReceiver = new UserReceiverImpl();
    
    /** The book order receiver. */
    private final BookOrderReceiver bookOrderReceiver = new BookOrderReceiverImpl();

    /**
     * Instantiates a new receiver factory.
     */
    private ReceiverFactory() {
    }

    /**
     * Gets the single instance of ReceiverFactory.
     *
     * @return single instance of ReceiverFactory
     */
    public static ReceiverFactory getInstance() {
        return instance;
    }

    /**
     * Gets the book receiver.
     *
     * @return the book receiver
     */
    public BookReceiver getBookReceiver() {
        return bookReceiver;
    }

    /**
     * Gets the user receiver.
     *
     * @return the user receiver
     */
    public UserReceiver getUserReceiver() {
        return userReceiver;
    }

    /**
     * Gets the book order receiver.
     *
     * @return the book order receiver
     */
    public BookOrderReceiver getBookOrderReceiver() {
        return bookOrderReceiver;
    }
}
