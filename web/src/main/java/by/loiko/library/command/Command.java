package by.loiko.library.command;

import by.loiko.library.controller.Router;
import by.loiko.library.receiver.ReceiverFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface Command {
    ReceiverFactory factory = ReceiverFactory.getInstance();

    /**
     * execute request
     *
     * @param request the HttpServletRequest
     * @return router
     */
    Router execute(HttpServletRequest request);

    /**
     * Get all parameters of request
     *
     * @param request the HttpServletRequest
     * @return Map<String, String> parameters
     */
    default Map<String, String> getAllParametersAsMap(HttpServletRequest request) {
        Enumeration enumeration = request.getParameterNames();
        Map<String, String> paramMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            //paramValue = new String(paramValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            paramMap.put(paramName, paramValue);
        }

        return paramMap;
    }

}
