package by.loiko.library.command.navigate;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.01.2018
 ***/
public class ToAddBookPageCommand implements Command {
    private static final String MESSAGE_PARAM = "message";
    private static final String GENRES_PARAM = "genres";
    private static final String AUTHORS_PARAM = "authors";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            List<Genre> genreList = factory.getBookReceiver().findAllGenres();
            List<Author> authorList = factory.getBookReceiver().findAllAuthors();

            request.setAttribute(AUTHORS_PARAM, authorList);
            request.setAttribute(GENRES_PARAM, genreList);
            router.setPagePath(PageConstant.ADD_BOOK_FORM);
        } catch (ReceiverException e) {
            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        // request.getSession().setAttribute("url", UrlConstant.SIGN_UP);
        return router;
    }
}
