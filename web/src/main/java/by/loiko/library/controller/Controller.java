package by.loiko.library.controller;

import by.loiko.library.command.Command;
import by.loiko.library.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 Author: Aliaksei Loika
 Date: 14.12.2017
 ***/

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router;

        Command command = CommandProvider.getInstance().getCommand(request);

        router = command.execute(request);
        switch (router.getRouteType()) {
            case FORWARD:
                //
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
        }

    }

}
