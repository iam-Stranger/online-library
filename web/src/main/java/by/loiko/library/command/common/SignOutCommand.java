package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 12.01.2018
 ***/
public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);

        request.getSession().invalidate();

        router.setPagePath(PageConstant.INDEX_PAGE);
        return router;
    }
}
