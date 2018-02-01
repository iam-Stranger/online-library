package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class EditAuthorInfoCommand implements Command {
    private static final String MESSAGE_PARAM = "message";
    private static final String AUTHOR_ID_PARAM = "id";
    private static final String AUTHOR_PARAM = "author";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long authorId;
        try {
            authorId = Long.valueOf(request.getParameter(AUTHOR_ID_PARAM));
        } catch (NumberFormatException e) {
            authorId = 0;
        }

        try{
            Author author = factory.getBookReceiver().findAuthorById(authorId);
            request.setAttribute(AUTHOR_PARAM, author);
            router.setPagePath(PageConstant.EDIT_AUTHOR_FORM);

        } catch (ReceiverException e) {

            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
