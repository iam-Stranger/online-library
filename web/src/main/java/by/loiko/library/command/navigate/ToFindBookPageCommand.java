package by.loiko.library.command.navigate;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 21.01.2018
 ***/
public class ToFindBookPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PageConstant.FIND_BOOK_FORM);

        request.getSession().setAttribute("url", UrlConstant.FIND_BOOK_PAGE);
        return router;
    }
}
