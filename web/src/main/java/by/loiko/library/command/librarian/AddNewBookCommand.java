package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 28.01.2018
 ***/
public class AddNewBookCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);
        String[] genresIdArray = request.getParameterValues(ParamConstant.GENRES_ARRAY_PARAM);
        String[] authorsIdArray = request.getParameterValues(ParamConstant.AUTHORS_ARRAY_PARAM);

        try {
            errorMap = factory.getBookReceiver().addNewBook(paramsMap, genresIdArray, authorsIdArray);

            if (errorMap.isEmpty()) {
                router.setPagePath(UrlConstant.SHOW_ALL_BOOKS);
                // add success  PAGE or message
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                List<Genre> genreList = factory.getBookReceiver().findAllGenres();
                List<Author> authorList = factory.getBookReceiver().findAllAuthors();

                request.setAttribute(ParamConstant.AUTHORS_LIST_PARAM, authorList);
                request.setAttribute(ParamConstant.GENRES_LIST_PARAM, genreList);

                request.setAttribute(ParamConstant.AUTHORS_ARRAY_PARAM, authorsIdArray);
                request.setAttribute(ParamConstant.GENRES_ARRAY_PARAM, genresIdArray);

                request.setAttribute(ParamConstant.ERROR_MAP_PARAM, errorMap);
                request.setAttribute(ParamConstant.PARAMS_MAP_PARAM, paramsMap);
                router.setPagePath(PageConstant.ADD_BOOK_FORM);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
