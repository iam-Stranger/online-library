package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class ChangeLocaleCommand implements Command {
    private static final String LOCALE_PARAM = "locale";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);
        //request.getSession().setAttribute(ParamConstant.URL_PARAM, request.getHeader(ParamConstant.REFERER_PARAM));

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(LOCALE_PARAM);

        if (locale == null || "en-EN".equals(locale)) {
            locale = "ru-RU";
        } else {
            locale = "en-EN";
        }
        
        session.setAttribute(LOCALE_PARAM, locale);
        router.setPagePath(request.getHeader(ParamConstant.REFERER_PARAM));

        return router;
    }
}
