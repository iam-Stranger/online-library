package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 Author: Aliaksei Loika
 Date: 11.01.2018
 ***/
public class GoToPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(Router.RouteType.REDIRECT);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            router.setPagePath("main.jsp");
        } else {
            router.setPagePath("login.jsp");
        }

        request.getSession().setAttribute("url", "controller?command=go_to_page");

        return router;
    }
}
