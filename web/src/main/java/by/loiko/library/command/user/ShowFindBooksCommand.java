package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.BookReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.01.2018
 ***/
public class ShowFindBooksCommand implements Command {
    private static final String BOOK_LIST_PARAM = "book_list";
    private static final String TITLE_PARAM = "title";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String title = request.getParameter(TITLE_PARAM);
        List<Book> booksList;

        try {
            BookReceiver bookReceiver = factory.getBookReceiver();
            booksList = bookReceiver.findBookByTitle(title);

            request.setAttribute(BOOK_LIST_PARAM, booksList);
            router.setPagePath(PageConstant.SHOW_FIND_BOOKS);

        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }


        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
