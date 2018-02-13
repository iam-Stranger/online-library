package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.receiver.ReceiverException;
import by.loiko.library.receiver.BookReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 12.02.2018
 ***/
public class ShowFindBooksByGenreCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String id = request.getParameter(ParamConstant.GENRE_ID_PARAM);

        try {
            BookReceiver bookReceiver = factory.getBookReceiver();
            List<Book> booksList = bookReceiver.findBooksByGenreId(id);

            request.setAttribute(ParamConstant.BOOK_LIST_PARAM, booksList);
            router.setPagePath(PageConstant.SHOW_FIND_BOOKS);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
