package by.loiko.library.command.user;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.command.ParamConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.User;
import by.loiko.library.receiver.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 23.01.2018
 ***/
public class ShowCurrentOrderListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String[] bookIdArray = request.getParameterValues(ParamConstant.BOOK_ID_ARRAY_PARAM);
        User user = (User) request.getSession().getAttribute(ParamConstant.USER_OBJ_PARAM);

        try {
            boolean noDebt = factory.getBookOrderReceiver().checkNoDebtBooksByUser(user);
            int countPossibleBooks = factory.getBookOrderReceiver().findCountPossibleBooksByUser(user);

            if (noDebt){
                if (countPossibleBooks < 1){
                    router.setPagePath(PageConstant.DIALOG_LIMIT_BOOK);
                }else{
                    List<Book> booksList = factory.getBookReceiver().findBooksByArrayOfId(bookIdArray);
                    request.setAttribute(ParamConstant.BOOK_LIST_PARAM, booksList);
                    request.setAttribute(ParamConstant.COUNT_POSSIBLE_BOOKS, countPossibleBooks);
                    router.setPagePath(PageConstant.SHOW_CURRENT_ORDER_LIST);
                }
            }else {
                router.setPagePath(PageConstant.DIALOG_EXPIRED_BOOK);
            }

        } catch (ReceiverException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRouteType(Router.RouteType.REDIRECT);
        }


        return router;
    }
}
