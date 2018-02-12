package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.BookOrder;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 26.01.2018
 ***/
public class ManageOrdersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            List<BookOrder> ordersList = factory.getBookOrderReceiver().findAllBookOrders();

            request.setAttribute(ParamConstant.ORDER_LIST_PARAM, ordersList);
            router.setPagePath(PageConstant.MANAGE_ORDERS);
        } catch (ReceiverException e) {

            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
