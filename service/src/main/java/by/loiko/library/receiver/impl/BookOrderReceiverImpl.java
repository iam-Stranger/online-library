package by.loiko.library.receiver.impl;

import by.loiko.library.dao.BookOrderDAO;
import by.loiko.library.dao.DAOFactory;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookOrderReceiver;
import by.loiko.library.validator.EntityValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class BookOrderReceiverImpl implements BookOrderReceiver {
    private static Logger logger = LogManager.getLogger();

    private BookOrderDAO bookOrderDAO = DAOFactory.getInstance().getBookOrderDAO();

    @Override
    public List<BookOrder> findAllBookOrdersAbs() throws ReceiverException {
        List<BookOrder> ordersList;

        try {
            ordersList = bookOrderDAO.findAllEntities();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllBookOrdersAbs command wasn't executed", e);
        }

        return ordersList;
    }

    @Override
    public List<BookOrder> findAllBookOrders() throws ReceiverException {
        List<BookOrder> ordersList;

        try {
            ordersList = bookOrderDAO.findAllBookOrders();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllBookOrders command wasn't executed", e);
        }

        return ordersList;
    }

    @Override
    public List<BookOrder> findBookOrdersByUser(User user) throws ReceiverException {
        List<BookOrder> ordersList;

        long userId;
        if (user != null) {
            userId = user.getId();
        } else {
            throw new ReceiverException("User is incorrect");
        }

        try {
            ordersList = bookOrderDAO.findBookOrdersByUserId(userId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findBookOrdersByUser command wasn't executed", e);
        }

        return ordersList;
    }

    public void changeBookOrderStatusToIssued(String id, String orderType, String date) throws ReceiverException {
        EntityValidator entityValidator = new EntityValidator();
        long bookOrderId;
        int OrderTypeId;
        String dateFrom;

        if (entityValidator.validateId(id)) {
            bookOrderId = Long.parseLong(id);
        } else {
            throw new ReceiverException("OrderID is incorrect");
        }
        if (entityValidator.validateOrderType(orderType)) {
            OrderTypeId = Integer.parseInt(orderType);
        } else {
            throw new ReceiverException("Order type is incorrect");
        }
        if (entityValidator.validateDate(date)) {
            dateFrom = date;
        } else {
            throw new ReceiverException("Date is incorrect");
        }

        try {
            bookOrderDAO.changeBookOrderStatusToIssued(bookOrderId, OrderTypeId, dateFrom);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("changeBookOrderStatusToIssued command wasn't executed: ", e);
        }

    }

    @Override
    public void changeBookOrderStatusToCanceled(String id) throws ReceiverException {
        EntityValidator entityValidator = new EntityValidator();
        long bookOrderId;

        if (entityValidator.validateId(id)) {
            bookOrderId = Long.parseLong(id);
        } else {
            throw new ReceiverException("OrderID is incorrect");
        }

        try {
            bookOrderDAO.changeBookOrderStatusToCanceled(bookOrderId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("changeBookOrderStatusToCanceled command wasn't executed: ", e);
        }

    }

    @Override
    public void changeBookOrderStatusToReturned(String id) throws ReceiverException {
        EntityValidator entityValidator = new EntityValidator();
        long bookOrderId;

        if (entityValidator.validateId(id)) {
            bookOrderId = Long.parseLong(id);
        } else {
            throw new ReceiverException("OrderID is incorrect");
        }

        String dateReturn = takeCurrentDate();

        try {
            bookOrderDAO.changeBookOrderStatusToReturned(bookOrderId, dateReturn);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("changeBookOrderStatusToReturned command wasn't executed: ", e);
        }

    }

    @Override
    public int findCountPossibleBooksByUser(User user) throws ReceiverException {
        int count;
        long userId;
        if (user != null) {
            userId = user.getId();
        } else {
            throw new ReceiverException("User is incorrect");
        }

        try {
            count = bookOrderDAO.findCountCurrentBooksByUserId(userId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findCountCurrentBooksByUser command wasn't executed: ", e);
        }

        return 3 - count;
    }

    @Override
    public boolean checkNoDebtBooksByUser(User user) throws ReceiverException {
        int countExpiredBooks;

        long userId;
        if (user != null) {
            userId = user.getId();
        } else {
            throw new ReceiverException("User is incorrect");
        }

        String currentDate = takeCurrentDate();

        try{
           countExpiredBooks = bookOrderDAO.findCountExpiredBooksByUserIdAndDate(userId, currentDate);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("checkNoDebtBooksByUser command wasn't executed: ", e);
        }

        return countExpiredBooks == 0;
    }

    @Override
    public void createNewUserOrders(User user, String[] orderTypesStr, String[] booksIdStr) throws ReceiverException {
        EntityValidator entityValidator = new EntityValidator();
        long userId;
        if (user != null) {
            userId = user.getId();
        } else {
            throw new ReceiverException("User is incorrect");
        }

        List<Long> bookIdList = entityValidator.validateArrayOfId(booksIdStr);
        List<Integer> orderTypeList = entityValidator.validateArrayOfOrderTypeId(orderTypesStr);

        if (bookIdList.size() != orderTypeList.size()){
            throw new ReceiverException("Data is incorrect");
        }

        String currentDate = takeCurrentDate();

        for (int i=0; i<bookIdList.size(); i++){
            try{
                bookOrderDAO.createNewOrderWithStatusOrdered(userId, bookIdList.get(i), currentDate, orderTypeList.get(i) );
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("createNewUserOrders command wasn't executed: ", e);
            }
        }
    }

    private String takeCurrentDate() {
        return LocalDate.now().toString();
    }
}
