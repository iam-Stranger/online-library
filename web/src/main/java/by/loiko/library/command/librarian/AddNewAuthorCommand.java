package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.UrlConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 28.01.2018
 ***/
public class AddNewAuthorCommand implements Command {
    private static final String MESSAGE_PARAM = "message";
    private static final String ERROR_MAP_PARAM = "errors";
    private static final String PARAMS_MAP_PARAM = "params";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> errorMap = null;
        Map<String, String> paramsMap = getAllParametersAsMap(request);

        try {
            errorMap = factory.getBookReceiver().addNewAuthor(paramsMap);

            if (errorMap.isEmpty()) {
                router.setPagePath(UrlConstant.SHOW_ALL_AUTHORS);  // add success  PAGE or message
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(ERROR_MAP_PARAM, errorMap);
                request.setAttribute(PARAMS_MAP_PARAM, paramsMap);
                router.setPagePath(PageConstant.ADD_AUTHOR_FORM);
            }

        } catch (ReceiverException e) {
            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        return router;
    }
}
