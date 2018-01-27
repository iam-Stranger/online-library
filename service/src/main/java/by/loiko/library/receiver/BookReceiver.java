package by.loiko.library.receiver;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface BookReceiver extends AbstractReceiver {

    ArrayList<Book> findBookByTitle(String title) throws ReceiverException;

    ArrayList<Book> findBooksByArrayOfId(String[] idArray) throws ReceiverException;

    Book findBookById(long id) throws ReceiverException;

    void addNewBook(String title, int publishYear, int amount) throws ReceiverException;
}
