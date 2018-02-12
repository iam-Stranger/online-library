package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public class DialogDeleteCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> paramsMap = getAllParametersAsMap(request);

        request.setAttribute(ParamConstant.PARAMS_MAP_PARAM, paramsMap);

        router.setPagePath(PageConstant.DIALOG_DELETE);

        return router;
    }
}
