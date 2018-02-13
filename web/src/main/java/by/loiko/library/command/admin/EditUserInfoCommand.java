package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 25.01.2018
 ***/
public class EditUserInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String id = request.getParameter(ParamConstant.USER_ID_PARAM);

        try{
            User user = factory.getUserReceiver().findUserById(id);
            request.setAttribute(ParamConstant.USER_OBJ_PARAM, user);
            router.setPagePath(PageConstant.EDIT_USER_FORM);

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
