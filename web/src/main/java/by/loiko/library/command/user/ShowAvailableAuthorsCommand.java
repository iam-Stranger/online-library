package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.01.2018
 ***/
public class ShowAvailableAuthorsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            List<Author> authorList = factory.getBookReceiver().findAllNotEmptyAuthors();
            request.setAttribute(ParamConstant.AUTHORS_LIST_PARAM, authorList);
            router.setPagePath(PageConstant.FIND_BY_AUTHOR_FORM);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
