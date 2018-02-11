package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 11.01.2018
 ***/
public class GoToPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);

        User user = (User) request.getSession().getAttribute(ParamConstant.USER_PARAM);

        if (user != null) {
            router.setPagePath(UrlConstant.MAIN_PAGE);
        } else {
            router.setPagePath(PageConstant.SIGN_IN_FORM);
        }

        request.getSession().setAttribute(ParamConstant.URL_PARAM, request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
