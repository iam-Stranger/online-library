package by.loiko.library.receiver;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface BookReceiver {

    Book findBookByTitle(String title) throws ReceiverException; // List<Book> ?

    void addNewBook(String title, int publishYear, int amount) throws ReceiverException;
}
