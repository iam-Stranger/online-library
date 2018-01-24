package by.loiko.library.command.common;

import by.loiko.library.command.Command;
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
    private final static String USER_PARAM = "user";
    private final static String LOGIN_PARAM = "login";
    private final static String PWD_PARAM = "password";
    private static final String PAGE = "main.jsp";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PWD_PARAM);

        try {
            UserReceiver userReceiver = factory.getUserReceiver();
            User user = userReceiver.findUser(login, password);

            HttpSession session = request.getSession();
            session.setAttribute(USER_PARAM, user);

            request.setAttribute(USER_PARAM, user);
            router.setPagePath(PAGE);
        } catch (ReceiverException e) {
            // go to error page
            request.setAttribute("message", e.getMessage());
            router.setPagePath("/jsp/error_page.jsp");
        }

        return router;
    }
}
