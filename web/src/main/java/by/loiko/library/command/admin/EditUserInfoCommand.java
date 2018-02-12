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

        String id = request.getParameter(USER_ID_PARAM);

        try{
            User user = factory.getUserReceiver().findUserById(id);
            request.setAttribute(USER_PARAM, user);
            router.setPagePath(PageConstant.EDIT_USER_FORM);

        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
