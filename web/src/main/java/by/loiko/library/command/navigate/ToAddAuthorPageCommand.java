package by.loiko.library.command.navigate;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 21.01.2018
 ***/
public class ToAddAuthorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PageConstant.ADD_AUTHOR_FORM);

        request.getSession().setAttribute(ParamConstant.URL_PARAM, request.getHeader(ParamConstant.REFERER_PARAM));
        return router;
    }
}
