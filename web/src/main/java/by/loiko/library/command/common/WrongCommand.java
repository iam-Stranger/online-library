package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 14.01.2018
 ***/
public class WrongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        router.setPagePath("/jsp/error_page.jsp");
        request.setAttribute("message", "Wrong command");

        return router;
    }
}
