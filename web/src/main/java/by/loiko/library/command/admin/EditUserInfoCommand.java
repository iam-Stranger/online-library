package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 25.01.2018
 ***/
public class EditUserInfoCommand implements Command {
    private static final String USER_ID_PARAM = "id";
    private static final String USER_PARAM = "user";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long userId;
        try {
            userId = Long.valueOf(request.getParameter(USER_ID_PARAM));
        } catch (NumberFormatException e) {
            userId = 0;
        }

        try{
            User user = factory.getUserReceiver().findUserById(userId);
            request.setAttribute(USER_PARAM, user);
            router.setPagePath(PageConstant.EDIT_USER_FORM);

        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
