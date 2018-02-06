package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 28.01.2018
 ***/
public class UpdateBookInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);
        String[] genresIdArray = request.getParameterValues(ParamConstant.GENRES_ARRAY_PARAM);
        String[] authorsIdArray = request.getParameterValues(ParamConstant.AUTHORS_ARRAY_PARAM);

        try {
            errorMap = factory.getBookReceiver().updateBookInfo(paramsMap, genresIdArray, authorsIdArray);

            if (errorMap.isEmpty()) {
                router.setPagePath(UrlConstant.SHOW_ALL_BOOKS);
                // add success  PAGE or message
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                String id = request.getParameter(ParamConstant.BOOK_ID_PARAM);

                List<Genre> genreList = factory.getBookReceiver().findAllGenres();
                List<Author> authorList = factory.getBookReceiver().findAllAuthors();
                Book book = factory.getBookReceiver().findBookById(id);

                request.setAttribute(ParamConstant.AUTHORS_LIST_PARAM, authorList);
                request.setAttribute(ParamConstant.GENRES_LIST_PARAM, genreList);
                request.setAttribute(ParamConstant.BOOK_OBJ_PARAM, book);

                request.setAttribute(ParamConstant.ERROR_MAP_PARAM, errorMap);
                router.setPagePath(PageConstant.EDIT_BOOK_FORM);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
