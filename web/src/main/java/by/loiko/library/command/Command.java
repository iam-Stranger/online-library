package by.loiko.library.command;

import by.loiko.library.controller.Router;
import by.loiko.library.receiver.ReceiverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface Command {
    Logger logger = LogManager.getLogger();
    ReceiverFactory factory = ReceiverFactory.getInstance();

    Router execute(HttpServletRequest request);

        default Map<String, String> getAllParametersAsMap(HttpServletRequest request) {
        Enumeration enumeration = request.getParameterNames();
        Map<String, String> paramMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            paramValue = new String(paramValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            paramMap.put(paramName, paramValue);
        }

        return paramMap;
    }

    default Map<String, String[]> getAllParamArrayAsMap(HttpServletRequest request) {
        Enumeration enumeration = request.getParameterNames();
        Map<String, String[]> paramMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            String paramValues[] = new String[0];
            try {
                paramValues = request.getParameterValues(paramName);
            }catch (IndexOutOfBoundsException e){
                String paramValue = request.getParameter(paramName);
                paramValues[1] = new String(paramValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }

            paramMap.put(paramName, paramValues);
        }

        return paramMap;
    }
}
