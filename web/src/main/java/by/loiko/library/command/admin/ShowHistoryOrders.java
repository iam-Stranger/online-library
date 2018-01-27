package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.ReceiverException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class ShowHistoryOrders implements Command {
    private static final String ORDER_LIST_VAR = "order_list";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            ArrayList<BookOrder> ordersList = factory.getBookOrderReceiver().findAllBookOrders();
            request.setAttribute(ORDER_LIST_VAR, ordersList);
            router.setPagePath(PageConstant.SHOW_HISTORY_ORDERS);
        } catch (ReceiverException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", UrlConstant.SHOW_HISTORY_ORDERS);
        return router;
    }
}
