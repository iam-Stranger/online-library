package by.loiko.library.command.common;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 28.01.2018
 ***/
public class AddNewUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);

        try {
            errorMap = factory.getUserReceiver().AddNewUser(paramsMap);
            if (errorMap.isEmpty()) {
                router.setPagePath(PageConstant.SIGN_UP_SUCCESS);
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(ParamConstant.ERROR_MAP_PARAM, errorMap);
                request.setAttribute(ParamConstant.PARAMS_MAP_PARAM, paramsMap);
                router.setPagePath(PageConstant.SIGN_UP_FORM);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}