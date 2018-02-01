package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class DeleteBookCommand implements Command {
    private static final String BOOK_ID_PARAM = "id";
    private static final String MESSAGE_PARAM = "message";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long id;

        try {
            id = Long.parseLong(request.getParameter(BOOK_ID_PARAM));
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            factory.getBookReceiver().deleteBook(id);
            router.setPagePath(UrlConstant.SHOW_ALL_BOOKS);
            router.setRouteType(Router.RouteType.REDIRECT);

        } catch (ReceiverException | NumberFormatException e) {
            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
