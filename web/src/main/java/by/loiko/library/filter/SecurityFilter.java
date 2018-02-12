package by.loiko.library.filter;

import by.loiko.library.command.CommandProvider;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/***
 Author: Aliaksei Loika
 Date: 10.02.2018
 ***/
@WebFilter(urlPatterns = "/controller")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(ParamConstant.USER_OBJ_PARAM);

        int commandAccessLevel = CommandProvider.getInstance().getCommandAccessLevel(request);
        String commandName = CommandProvider.getInstance().getCommandName(request).toString();
        if (user != null) {

            if (commandAccessLevel > user.getRoleId()) {
                request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, "Access denied!!!");
                request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(servletRequest, servletResponse);
            }

        } else if (!isGuestCommand(commandName)) {
            request.getRequestDispatcher(PageConstant.LOGIN_PAGE).forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private boolean isGuestCommand(String commandName){
        switch (commandName){
            case "GO_TO_PAGE":
            case "SIGN_IN":
            case "SIGN_UP":
            case "TO_SIGN_IN_PAGE":
            case "TO_SIGN_UP_PAGE":
            case "CHANGE_LOCALE":
            case "ADD_NEW_USER":
            case "WRONG_COMMAND":
                return true;
            default:
                return false;
        }
    }

}
