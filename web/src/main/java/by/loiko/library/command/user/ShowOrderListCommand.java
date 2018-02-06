package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 23.01.2018
 ***/
public class ShowOrderListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String[] bookIdArray = request.getParameterValues(ParamConstant.BOOK_ID_ARRAY_PARAM);

        try {
            List<Book> booksList = factory.getBookReceiver().findBooksByArrayOfId(bookIdArray);
            request.setAttribute(ParamConstant.BOOK_LIST_PARAM, booksList);
            router.setPagePath(PageConstant.SHOW_ORDER_LIST);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        request.getSession().setAttribute(ParamConstant.URL_PARAM, request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
