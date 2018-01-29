package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 11.01.2018
 ***/
public class GoToPageCommand implements Command {
    private static final String PAGE_PARAM = "page";
    private static final String USER_PARAM = "user";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);

        User user = (User) request.getSession().getAttribute(USER_PARAM);
        String page = (String) request.getAttribute(PAGE_PARAM);

        if (user != null) {
            if (page != null) {

                ///

            } else {
                router.setPagePath(PageConstant.MAIN_PAGE);
            }
        } else {
            router.setPagePath(PageConstant.SIGN_IN);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
