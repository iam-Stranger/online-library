package by.loiko.library.receiver.impl;

import by.loiko.library.dao.BookOrderDAO;
import by.loiko.library.dao.DAOFactory;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookOrderReceiver;

import java.util.ArrayList;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class BookOrderReceiverImpl implements BookOrderReceiver {
    @Override
    public List<BookOrder> findAllBookOrders() throws ReceiverException {
        List<BookOrder> ordersList = new ArrayList<>();

        try {
            BookOrderDAO bookOrderDAO = DAOFactory.getInstance().getBookOrderDAO();
            ordersList = bookOrderDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findBooksByArrayOfId command wasn't executed: ", e);
        }


        return ordersList;
    }
}