package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 09.02.2018
 ***/
public class CreateOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        User user = (User) request.getSession().getAttribute(ParamConstant.USER_PARAM);
        String[] orderTypes = request.getParameterValues(ParamConstant.ORDER_TYPE_PARAM);
        String[] items = request.getParameterValues(ParamConstant.BOOK_ID_ARRAY_PARAM);

        try {
            int countPossibleBooks = factory.getBookOrderReceiver().findCountPossibleBooksByUser(user);

            if ( items == null || countPossibleBooks < items.length){
                router.setPagePath(request.getHeader(ParamConstant.REFERER_PARAM));
                router.setRouteType(Router.RouteType.REDIRECT);
            }else {
               factory.getBookOrderReceiver().createNewUserOrders(user, orderTypes, items);
               // success page
               router.setPagePath(UrlConstant.SHOW_ACTIVE_USER_ORDERS);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }


        return router;
    }
}
