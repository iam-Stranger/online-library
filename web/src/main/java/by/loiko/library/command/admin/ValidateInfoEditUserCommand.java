package by.loiko.library.command.admin;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 27.01.2018
 ***/
public class ValidateInfoEditUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Map<String, String> paramsMap = null;
        Map<String, String> errorMap = null;


        try {
            paramsMap = getAllParametersAsMap(request);
            UserValidator userValidator = new UserValidator();
            errorMap = userValidator.validateEditUser(paramsMap);


//            if (!errorMap.isEmpty()) {
//                for (Map.Entry entry : errorMap.entrySet()) {
//                    String key = (String) entry.getKey();
//                    String value = (String) entry.getValue();
//                    System.out.println(key + " " + value);
//                }
//            }else {
//                System.out.println("is ok");
//            }
        } catch (ReceiverException e) {
            router.setPagePath(PageConstant.ERROR_PAGE);
        }



        return router;
    }
}
