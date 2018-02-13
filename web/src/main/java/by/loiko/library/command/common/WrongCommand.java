package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 14.01.2018
 ***/
public class WrongCommand implements Command {
    private static final String WRONG_COMMAND = "Wrong command";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, WRONG_COMMAND);
        router.setPagePath(PageConstant.ERROR_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
