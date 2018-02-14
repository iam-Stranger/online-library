package by.loiko.library.validator;

import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.dao.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 Author: Aliaksei Loika
 Date: 27.01.2018
 ***/
public class EntityValidator {
    private static Logger logger = LogManager.getLogger();

    private final static String MULTISELECT_MSG = "You must select at least 1 value";
    private final static String REAL_AMOUNT_MSG = "Real amount can not be more then total amount";
    private final static String UNEXPECTED_MSG = "Unexpected error, please try again";
    private final static String LOGIN_USED_MSG = "Login is already is used";
    private final static String EMAIL_USED_MSG = "E-mail is already is used";



    /**
     * Validate field id.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean validateId(String id) {
        return !checkBadParameter(FieldEnum.ID.getRegEx(), id);
    }

    /**
     * Validate field order_type.
     *
     * @param orderTypeId the order type id
     * @return true, if successful
     */
    public boolean validateOrderType(String orderTypeId) {
        return !checkBadParameter(FieldEnum.ORDER_TYPE.getRegEx(), orderTypeId);
    }

    /**
     * Validate field date_from.
     *
     * @param date the date
     * @return true, if successful
     */
    public boolean validateDate(String date) {
        return !checkBadParameter(FieldEnum.DATE_FROM.getRegEx(), date);
    }

    /**
     * Validate new user.
     *
     * @param paramsMap the params map
     * @return the hash map of errors
     */
    public HashMap<String, String> validateNewUser(Map<String, String> paramsMap) {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        HashMap<String, String> errorMap = validateParams(paramsMap);

        User loginOwner = null;
        User emailOwner = null;
        try {
            loginOwner = userDAO.findUserByLogin(paramsMap.get(FieldEnum.LOGIN.getFieldName()));
            emailOwner = userDAO.findUserByEmail(paramsMap.get(FieldEnum.EMAIL.getFieldName()));
        } catch (DAOException e) {
            errorMap.put(FieldEnum.LOGIN.getFieldName(), UNEXPECTED_MSG);
        }
        if (loginOwner != null) {
            errorMap.put(FieldEnum.LOGIN.getFieldName(),LOGIN_USED_MSG );
        }
        if (emailOwner != null) {
            errorMap.put(FieldEnum.EMAIL.getFieldName(),EMAIL_USED_MSG );
        }

        return errorMap;
    }


    /**
     * Validate updated user.
     *
     * @param paramsMap the params map
     * @return the hash map of errors
     */
    public HashMap<String, String> validateUpdatedUser(Map<String, String> paramsMap) {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        HashMap<String, String> errorMap = validateParams(paramsMap);

        User loginOwner = null;
        User emailOwner = null;

        long currId = Long.parseLong(paramsMap.get(FieldEnum.ID.getFieldName()));
        try {
            loginOwner = userDAO.findUserByLogin(paramsMap.get(FieldEnum.LOGIN.getFieldName()));
            emailOwner = userDAO.findUserByEmail(paramsMap.get(FieldEnum.EMAIL.getFieldName()));

        } catch (DAOException | NumberFormatException e ) {
            errorMap.put(FieldEnum.LOGIN.getFieldName(), UNEXPECTED_MSG);
        }

        if (loginOwner != null && loginOwner.getId() != currId) {
            errorMap.put(FieldEnum.LOGIN.getFieldName(), LOGIN_USED_MSG);
        }

        if (emailOwner != null && emailOwner.getId() != currId) {
            errorMap.put(FieldEnum.EMAIL.getFieldName(), EMAIL_USED_MSG);
        }

        return errorMap;
    }


    /**
     * Validate book.
     *
     * @param paramsMap the params map
     * @param genres    the genres
     * @param authors   the authors
     * @return the hash map of errors
     */
    public HashMap<String, String> validateBook(Map<String, String> paramsMap, String[] genres, String[] authors) {
        HashMap<String, String> errorMap = validateParams(paramsMap);

        if (genres != null) {
            for (String genre : genres) {
                if (checkBadParameter(FieldEnum.ID.getRegEx(), genre)) {
                    errorMap.put(FieldEnum.ID.getFieldName(), FieldEnum.ID.getMessage());
                }
            }
        } else {
            errorMap.put(FieldEnum.GENRE.getFieldName(), MULTISELECT_MSG);
        }

        if (authors != null) {
            for (String author : authors) {
                if (checkBadParameter(FieldEnum.ID.getRegEx(), author)) {
                    errorMap.put(FieldEnum.ID.getFieldName(), FieldEnum.ID.getMessage());
                }
            }
        } else {
            errorMap.put(FieldEnum.AUTHOR.getFieldName(), MULTISELECT_MSG);
        }

        int realAmount = Integer.parseInt(paramsMap.get(FieldEnum.REAL_AMOUNT.getFieldName()));
        int totalAmount = Integer.parseInt(paramsMap.get(FieldEnum.REAL_AMOUNT.getFieldName()));
        if (realAmount > totalAmount) {
            errorMap.put(FieldEnum.REAL_AMOUNT.getFieldName(), REAL_AMOUNT_MSG);
        }

        return errorMap;
    }

    /**
     * Validate array of id.
     *
     * @param idArray the id array
     * @return the array list
     */
    public ArrayList<Long> validateArrayOfId(String[] idArray) {
        ArrayList<Long> idList = new ArrayList<>();
        for (String id : idArray) {
            if (!checkBadParameter(FieldEnum.ID.getRegEx(), id)) {
                long bookId = Long.parseLong(id);
                idList.add(bookId);
            }
        }
        return idList;
    }

    /**
     * Validate array of order type id.
     *
     * @param statusIdArray the status id array
     * @return the array list
     */
    public ArrayList<Integer> validateArrayOfOrderTypeId(String[] statusIdArray) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (String id : statusIdArray) {
            if (!checkBadParameter(FieldEnum.ORDER_TYPE.getRegEx(), id)) {
                int statusId = Integer.parseInt(id);
                idList.add(statusId);
            }
        }
        return idList;
    }

    /**
     * Validate Map of parameters
     *
     * @param paramsMap the params map
     * @return the hash map
     */
    public HashMap<String, String> validateParams(Map<String, String> paramsMap) {
        HashMap<String, String> errorMap = new HashMap<>();

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {

            String current = entry.getKey();
            try {
                FieldEnum field = FieldEnum.valueOf(current.toUpperCase());

                if (checkBadParameter(field.getRegEx(), paramsMap.get(current))) {
                    errorMap.put(field.getFieldName(), field.getMessage());
                }

            } catch (IllegalArgumentException e) {
                logger.log(Level.DEBUG, "FieldEnum: parameter not present: " + current);
            }
        }

        return errorMap;
    }

    /**
     * Check bad parameter.
     *
     * @param regex the regex
     * @param line  the line
     * @return true, if parameter is bad
     */
    private boolean checkBadParameter(String regex, String line) {
        if (line == null) {
            return true;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        logger.log(Level.DEBUG, line + " " + matcher.matches());
        return !matcher.matches();
    }

}
