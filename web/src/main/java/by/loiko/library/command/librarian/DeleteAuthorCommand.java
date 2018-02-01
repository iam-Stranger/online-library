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
public class DeleteAuthorCommand implements Command {
    private static final String AUTHOR_ID_PARAM = "id";
    private static final String MESSAGE_PARAM = "message";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long id;

        try {
            id = Long.parseLong(request.getParameter(AUTHOR_ID_PARAM));
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            factory.getBookReceiver().deleteAuthor(id);
            router.setPagePath(UrlConstant.SHOW_ALL_AUTHORS);
            router.setRouteType(Router.RouteType.REDIRECT);

        } catch (ReceiverException | NumberFormatException e) {
            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
