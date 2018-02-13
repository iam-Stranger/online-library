package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class EditBookInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String id = request.getParameter(ParamConstant.BOOK_ID_PARAM);

        try {
            List<Genre> genreList = factory.getBookReceiver().findAllGenres();
            List<Author> authorList = factory.getBookReceiver().findAllAuthors();
            Book book = factory.getBookReceiver().findBookById(id);

            request.setAttribute(ParamConstant.AUTHORS_LIST_PARAM, authorList);
            request.setAttribute(ParamConstant.GENRES_LIST_PARAM, genreList);
            request.setAttribute(ParamConstant.BOOK_OBJ_PARAM, book);
            router.setPagePath(PageConstant.EDIT_BOOK_FORM);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
