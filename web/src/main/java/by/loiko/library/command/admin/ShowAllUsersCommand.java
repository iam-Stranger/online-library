package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.receiver.ReceiverException;
import by.loiko.library.receiver.UserReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class ShowAllUsersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<User> userList;
        try {
            UserReceiver userReceiver = factory.getUserReceiver();
            userList = userReceiver.findAllUsers();
            request.setAttribute(ParamConstant.USER_LIST_PARAM, userList);
            router.setPagePath(PageConstant.SHOW_ALL_USERS);
        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
