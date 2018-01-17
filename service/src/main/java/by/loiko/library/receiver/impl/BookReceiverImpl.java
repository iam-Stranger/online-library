package by.loiko.library.receiver.impl;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookReceiver;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class BookReceiverImpl implements BookReceiver {
    @Override
    public Book findBookByTitle(String title) throws ReceiverException {
        return null;
    }

    @Override
    public void addNewBook(String title, int publishYear, int amount) throws ReceiverException {

    }
}
