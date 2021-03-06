package by.loiko.library.filter;

import by.loiko.library.command.*;
import by.loiko.library.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/***
 Author: Aliaksei Loika
 Date: 10.02.2018
 ***/
@WebFilter(urlPatterns = "/jsp/admin/*")
public class AdminDirFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(ParamConstant.USER_OBJ_PARAM);
        if (user == null || user.getRoleId() < 3) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, "Access denied!");
            request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
