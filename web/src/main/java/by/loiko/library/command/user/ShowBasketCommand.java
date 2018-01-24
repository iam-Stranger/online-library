package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 23.01.2018
 ***/
public class ShowBasketCommand implements Command {
    private static final String BOOK_ID_ARRAY_PARAM = "items";
    private static final String BOOK_LIST_PARAM = "book_list";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String[] bookIdArray;

        bookIdArray = request.getParameterValues(BOOK_ID_ARRAY_PARAM);

        try {
            ArrayList<Book> booksList = factory.getBookReceiver().findBooksByArrayOfId(bookIdArray);
            request.setAttribute(BOOK_LIST_PARAM, booksList);
            router.setPagePath(PageConstant.SHOW_BASKET);
        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
