package by.loiko.library.validator;

import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.FieldConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 Author: Aliaksei Loika
 Date: 27.01.2018
 ***/
public class UserValidator {
    private static Logger logger = LogManager.getLogger();

    private static final String ID_REGEX = "[^-0]+\\d*";
    private static final String FIRSTNAME_REGEX = "[A-ZА-ЯЁ]{1}[a-zа-яё]{2,20}";
    private static final String LASTNAME_REGEX = "[A-ZА-ЯЁ]{1}[a-zа-яё]{3,20}";
    private static final String LOGIN_REGEX = "[a-zA-Z]{1}[a-zA-Z\\d]{3,19}";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9._*]{3,9}";
    private static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
    private static final String ROLE_ID_REGEX = "1|2|3";
    private static final String STATUS_REGEX = "0|1";

    public HashMap<String, String> validateEditUser(Map<String, String> paramsMap) throws ReceiverException {

        return validateUserParams(paramsMap);
    }

    public HashMap<String, String> validateUserParams(Map<String, String> paramsMap) {
        HashMap<String, String> errorMap = new HashMap<>();

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {

            switch (entry.getKey()) {
                case FieldConstant.ID_PARAM:
                    if (checkBadParameter(ID_REGEX, paramsMap.get(FieldConstant.ID_PARAM))) {
                        errorMap.put(FieldConstant.ID_PARAM, FieldConstant.ID_MSG);
                    }
                    break;
                case FieldConstant.FIRSTNAME_PARAM:
                    if (checkBadParameter(FIRSTNAME_REGEX, paramsMap.get(FieldConstant.FIRSTNAME_PARAM))) {
                        errorMap.put(FieldConstant.FIRSTNAME_PARAM, FieldConstant.FIRSTNAME_MSG);
                    }
                    break;
                case FieldConstant.LASTNAME_PARAM:
                    if (checkBadParameter(LASTNAME_REGEX, paramsMap.get(FieldConstant.LASTNAME_PARAM))) {
                        errorMap.put(FieldConstant.LASTNAME_PARAM, FieldConstant.LASTNAME_MSG);
                    }
                    break;
                case FieldConstant.LOGIN_PARAM:
                    if (checkBadParameter(LOGIN_REGEX, paramsMap.get(FieldConstant.LOGIN_PARAM))) {
                        errorMap.put(FieldConstant.LOGIN_PARAM, FieldConstant.LOGIN_MSG);
                    }
                    break;
                case FieldConstant.PASSWORD_PARAM:
                    if (checkBadParameter(PASSWORD_REGEX, paramsMap.get(FieldConstant.PASSWORD_PARAM))) {
                        errorMap.put(FieldConstant.PASSWORD_PARAM, FieldConstant.PASSWORD_MSG);
                    }
                    break;
                case FieldConstant.EMAIL_PARAM:
                    if (checkBadParameter(EMAIL_REGEX, paramsMap.get(FieldConstant.EMAIL_PARAM))) {
                        errorMap.put(FieldConstant.EMAIL_PARAM, FieldConstant.EMAIL_MSG);
                    }
                    break;
                case FieldConstant.ROLE_ID_PARAM:
                    if (checkBadParameter(ROLE_ID_REGEX, paramsMap.get(FieldConstant.ROLE_ID_PARAM))) {
                        errorMap.put(FieldConstant.ROLE_ID_PARAM, FieldConstant.ROLE_ID_MSG);
                    }
                    break;
                case FieldConstant.STATUS_PARAM:
                    if (checkBadParameter(STATUS_REGEX, paramsMap.get(FieldConstant.STATUS_PARAM))) {
                        errorMap.put(FieldConstant.STATUS_PARAM, FieldConstant.STATUS_MSG);
                    }
                    break;
                default:

            }
        }

        return errorMap;
    }

    private boolean checkBadParameter(String regex, String line) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        logger.log(Level.DEBUG, line + " " + matcher.matches());
        return !matcher.matches();
    }

}
