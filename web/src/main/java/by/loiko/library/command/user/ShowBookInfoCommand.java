package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 22.01.2018
 ***/
public class ShowBookInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String bookId = request.getParameter(ParamConstant.BOOK_ID_PARAM);

        try {
            Book book = factory.getBookReceiver().findBookById(bookId);
            request.setAttribute(ParamConstant.BOOK_OBJ_PARAM, book);
            router.setPagePath(PageConstant.SHOW_BOOK_INFO);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
