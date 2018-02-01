package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public class DialogDeleteCommand implements Command {
    private static final String PARAMS_MAP_ID_PARAM = "params";
    private static final String MESSAGE_PARAM = "message";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> paramsMap = getAllParametersAsMap(request);

        request.setAttribute(PARAMS_MAP_ID_PARAM, paramsMap);

        router.setPagePath(PageConstant.DIALOG_DELETE);

        return router;
    }
}
