package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.constant.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.UserReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class ShowAllUsers implements Command {
    private static final String USER_LIST = "user_list";
    private static final String COMMAND_URL = "controller?command=show_all_users";  //???

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        ArrayList<User> userList;
        try {
            UserReceiver userReceiver = factory.getUserReceiver();
            userList = userReceiver.findAllUsers();
            request.setAttribute(USER_LIST, userList);
            router.setPagePath(PageConstant.ALL_USERS);
        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", COMMAND_URL);  /// ???
        return router;
    }
}
