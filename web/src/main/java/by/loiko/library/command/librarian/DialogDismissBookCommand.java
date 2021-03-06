package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public class DialogDismissBookCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String id  = request.getParameter(ParamConstant.ORDER_ID_PARAM);

        request.setAttribute(ParamConstant.ORDER_ID_PARAM, id);

        router.setPagePath(PageConstant.DIALOG_DISMISS_BOOK);

        return router;
    }
}
