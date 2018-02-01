package by.loiko.library.receiver.impl;

import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.exception.ReceiverException;
import by.loiko.library.receiver.FieldConstant;
import by.loiko.library.receiver.UserReceiver;
import by.loiko.library.validator.UserValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class UserReceiverImpl implements UserReceiver {
    @Override
    public List<User> findAllUsers() throws ReceiverException {
        List<User> userList;

        try {
            DAOFactory daoFactoryObj = DAOFactory.getInstance();
            UserDAO userDAO = daoFactoryObj.getUserDAO();
            userList = userDAO.findAllEntities();
        } catch (DAOException e) {
            throw new ReceiverException("findAllUsers command wasn't executed: ", e);
        }

        return userList;
    }

    @Override
    public User findUser(String login, String password) throws ReceiverException {

        if ("".equals(login) || "".equals(password) || login == null || password == null) {
            throw new ReceiverException("login or password is empty");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ReceiverException("findUserByLoginAndPassword command wasn't executed: ", e);
        }

        if (user == null) {
            throw new ReceiverException("Login or password is incorrect");
        }

        return user;
    }

    @Override
    public User findUserById(long id) throws ReceiverException {
        if (id <= 0) {
            throw new ReceiverException("User ID is incorrect");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new ReceiverException("findUserById command error: ", e);
        }

        if (user == null) {
            throw new ReceiverException("User with this id is not found");
        }

        return user;
    }

    @Override
    public Map<String, String> AddNewUser(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("AddNewUser command: data is empty ");
        }

        UserValidator userValidator = new UserValidator();
        HashMap<String, String> errorMap = userValidator.validateUserParams(paramsMap);

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean isPresentLogin = userDAO.isUserPresentByLogin(paramsMap.get(FieldConstant.LOGIN_PARAM));
            boolean isPresentEmail = userDAO.isUserPresentByEmail(paramsMap.get(FieldConstant.EMAIL_PARAM));

            if (isPresentLogin) {
                errorMap.put(FieldConstant.LOGIN_PARAM, FieldConstant.LOGIN_PRESENT_MSG);
            }
            if (isPresentEmail) {
                errorMap.put(FieldConstant.EMAIL_PARAM, FieldConstant.EMAIL_PRESENT_MSG);
            }
        } catch (DAOException e) {
            throw new ReceiverException("isUserPresentByParam command wasn't executed: ", e);
        }

        if (errorMap.isEmpty()) {

            try {
                userDAO.addNewEntity(buildUser(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("addNewUser command wasn't executed: ", e);
            }
        }

        return errorMap;
    }

    @Override
    public boolean signIn(String login, String password) throws ReceiverException {
        return false;
    }

    private User buildUser(Map<String, String> paramsMap) {
        User user = new User();

        user.setLogin(paramsMap.get(FieldConstant.LOGIN_PARAM));
        user.setPassword(paramsMap.get(FieldConstant.PASSWORD_PARAM));
        user.setEmail(paramsMap.get(FieldConstant.EMAIL_PARAM));
        user.setFirstName(paramsMap.get(FieldConstant.FIRSTNAME_PARAM));
        user.setLastName(paramsMap.get(FieldConstant.LASTNAME_PARAM));

        if (paramsMap.get(FieldConstant.ROLE_ID_PARAM) != null) {
            int roleId = Integer.parseInt(paramsMap.get(FieldConstant.ROLE_ID_PARAM));
            user.setRoleId(roleId);
        }
        if (paramsMap.get(FieldConstant.STATUS_PARAM) != null) {
            int intDeleted = Integer.parseInt(paramsMap.get(FieldConstant.STATUS_PARAM));
            boolean isDeleted = (intDeleted != 0);
            user.setIsDeleted(isDeleted);
        }

        return user;
    }
}
