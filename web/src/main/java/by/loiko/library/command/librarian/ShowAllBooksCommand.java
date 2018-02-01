package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.exception.ReceiverException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.01.2018
 ***/
public class ShowAllBooksCommand implements Command {
    private static final String MESSAGE_PARAM = "message";
    private static final String BOOK_LIST_PARAM = "book_list";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            List<Book> bookList = factory.getBookReceiver().findAllBooks();
            request.setAttribute(BOOK_LIST_PARAM, bookList);
            router.setPagePath(PageConstant.SHOW_ALL_BOOKS);

        } catch (ReceiverException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
