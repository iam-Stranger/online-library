package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class EditGenreInfoCommand implements Command {
    private static final String GENRE_ID_PARAM = "id";
    private static final String GENRE_PARAM = "genre";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long genreId;
        try {
            genreId = Long.valueOf(request.getParameter(GENRE_ID_PARAM));
        } catch (NumberFormatException e) {
            genreId = 0;
        }

        try{
            Genre genre = factory.getBookReceiver().findGenreById(genreId);
            request.setAttribute(GENRE_PARAM, genre);
            router.setPagePath(PageConstant.EDIT_GENRE_FORM);

        } catch (ReceiverException e) {

            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
