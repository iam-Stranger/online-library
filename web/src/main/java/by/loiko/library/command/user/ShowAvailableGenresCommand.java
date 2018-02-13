package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Genre;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.01.2018
 ***/
public class ShowAvailableGenresCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            List<Genre> genreList = factory.getBookReceiver().findAllNotEmptyGenres();
            request.setAttribute(ParamConstant.GENRES_LIST_PARAM, genreList);
            router.setPagePath(PageConstant.FIND_BY_GENRE_FORM);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
