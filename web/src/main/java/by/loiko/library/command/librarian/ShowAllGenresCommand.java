package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.01.2018
 ***/
public class ShowAllGenresCommand implements Command {
    private static final String GENRE_LIST_PARAM = "genre_list";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try{
            List<Genre> genreList = factory.getBookReceiver().findAllGenres();
            request.setAttribute(GENRE_LIST_PARAM, genreList);
            router.setPagePath(PageConstant.SHOW_ALL_GENRES);

        } catch (ReceiverException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }


        return router;
    }
}
