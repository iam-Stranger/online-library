package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class GiveBookToUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String id = request.getParameter(ParamConstant.ORDER_ID_PARAM);
        String date = request.getParameter(ParamConstant.ORDER_DATE_TO_PARAM);
        String order_type = request.getParameter(ParamConstant.ORDER_TYPE_PARAM);

        try {
            factory.getBookOrderReceiver().changeBookOrderStatusToIssued(id, order_type, date);
            router.setPagePath(UrlConstant.MANAGE_ORDERS);
            router.setRouteType(Router.RouteType.REDIRECT);

        } catch (ReceiverException | NumberFormatException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
