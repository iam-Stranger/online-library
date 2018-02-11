package by.loiko.library.filter;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/***
 Author: Aliaksei Loika
 Date: 11.02.2018
 ***/
public class EncodingfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        servletResponse.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
