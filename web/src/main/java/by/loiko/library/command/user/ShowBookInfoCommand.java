package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 22.01.2018
 ***/
public class ShowBookInfoCommand implements Command {
    private static final String BOOK_PARAM = "book";
    private static final String BOOK_ID_PARAM = "id";


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long bookId;

        try {
            bookId = Long.parseLong(request.getParameter(BOOK_ID_PARAM));
        } catch (NumberFormatException e) {
            bookId = 0;
        }

        try {
            Book book = factory.getBookReceiver().findBookById(bookId);
            request.setAttribute(BOOK_PARAM, book);
            router.setPagePath(PageConstant.SHOW_BOOK_INFO);

        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
