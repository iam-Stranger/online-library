package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 28.01.2018
 ***/
public class ValidateInfoNewUserCommand implements Command {
    private static final String ERROR_MAP_PARAM = "errors";
    private static final String PARAMS_MAP_PARAM = "params";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);

        try {
            errorMap = factory.getUserReceiver().AddNewUser(paramsMap);
            if (errorMap.isEmpty()) {
                request.setAttribute("message","Congratulation!");
                router.setPagePath(PageConstant.SIGN_IN);
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(ERROR_MAP_PARAM, errorMap);
                request.setAttribute(PARAMS_MAP_PARAM, paramsMap);
                router.setPagePath(PageConstant.SIGN_UP);
            }

        } catch (ReceiverException e) {
            request.setAttribute("message", e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

//        if(!errorMap.isEmpty()){
//            for(Map.Entry<String, String> entry : errorMap.entrySet()){
//                System.out.println(entry.getValue());
//            }
//        }
//
//        if(!paramsMap.isEmpty()){
//            for(Map.Entry<String, String> entry : paramsMap.entrySet()){
//                System.out.println(entry.getValue());
//            }
//        }

        return router;
    }
}
