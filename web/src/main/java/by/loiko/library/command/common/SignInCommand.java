package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.UserReceiver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 Author: Aliaksei Loika
 Date: 11.01.2018
 ***/
public class SignInCommand implements Command {
    private final static String LOGIN_PARAM = "login";
    private final static String PWD_PARAM = "password";


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);

        //String locale = Locale.LanguageRange.parse(request.getHeader("Accept-Language")).get(1).getRange();

        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PWD_PARAM);

        try {
            UserReceiver userReceiver = factory.getUserReceiver();
            User user = userReceiver.findUser(login, password);

            HttpSession session = request.getSession();
            session.setAttribute(ParamConstant.USER_OBJ_PARAM, user);

            router.setPagePath(UrlConstant.MAIN_PAGE);
        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
