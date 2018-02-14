package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.User;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 27.01.2018
 ***/
public class UpdateUserInfoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);


        try {
            errorMap = factory.getUserReceiver().updateUserInfo(paramsMap);

            if (errorMap.isEmpty()) {
                request.getSession().setAttribute(ParamConstant.RETURN_PAGE_PARAM, UrlConstant.SHOW_ALL_USERS);
                router.setPagePath(PageConstant.DIALOG_SUCCESS);
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                String id = request.getParameter(ParamConstant.USER_ID_PARAM);

                User user = factory.getUserReceiver().findUserById(id);

                request.setAttribute(ParamConstant.USER_OBJ_PARAM, user);

                request.setAttribute(ParamConstant.ERROR_MAP_PARAM, errorMap);
                router.setPagePath(PageConstant.EDIT_USER_FORM);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
