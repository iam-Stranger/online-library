package by.loiko.library.receiver.impl;

import by.loiko.library.dao.DAOFactory;
import by.loiko.library.dao.UserDAO;
import by.loiko.library.entity.User;
import by.loiko.library.exception.DAOException;
import by.loiko.library.receiver.ReceiverException;
import by.loiko.library.receiver.UserReceiver;
import by.loiko.library.util.PasswordEncoder;
import by.loiko.library.validator.EntityValidator;
import by.loiko.library.validator.FieldEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class UserReceiverImpl implements UserReceiver {
    private static Logger logger = LogManager.getLogger();
    private EntityValidator entityValidator = new EntityValidator();

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#findAllUsers()
     */
    @Override
    public List<User> findAllUsers() throws ReceiverException {
        List<User> userList;

        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            userList = userDAO.findAllEntities();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findAllUsers command wasn't executed: ", e);
        }

        return userList;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#findUser(java.lang.String, java.lang.String)
     */
    @Override
    public User findUser(String login, String password) throws ReceiverException {

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new ReceiverException("login or password is empty");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            String encodePass = PasswordEncoder.encodePassword(password);

            user = userDAO.findUserByLoginAndPassword(login, encodePass);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findUserByLoginAndPassword command wasn't executed", e);
        }

        if (user == null) {
            throw new ReceiverException("Login or password is incorrect or user blocked");
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#findUserById(java.lang.String)
     */
    @Override
    public User findUserById(String id) throws ReceiverException {
        EntityValidator validator = new EntityValidator();
        long userId;
        if (validator.validateId(id)) {
            userId = Long.parseLong(id);
        } else {
            throw new ReceiverException("User ID is incorrect");
        }

        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.findEntityById(userId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("findUserById command wasn't executed", e);
        }

        if (user == null) {
            throw new ReceiverException("User was not found");
        }

        return user;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#AddNewUser(java.util.Map)
     */
    @Override
    public Map<String, String> AddNewUser(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }
        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateNewUser(paramsMap);

        if (errorMap.isEmpty()) {
            try {
                UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
                userDAO.addNewEntity(buildUser(paramsMap));
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("addNewUser command wasn't executed", e);
            }
        }

        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#changePassword(java.util.Map)
     */
    @Override
    public Map<String, String> changePassword(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        EntityValidator entityValidator = new EntityValidator();
        HashMap<String, String> errorMap = entityValidator.validateParams(paramsMap);

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        if (errorMap.isEmpty()) {
            try {
                Long userId = Long.parseLong(paramsMap.get(FieldEnum.ID.getFieldName()));
                String userLogin = paramsMap.get(FieldEnum.LOGIN.getFieldName());
                String newPassword = PasswordEncoder.encodePassword(paramsMap.get(FieldEnum.PASSWORD.getFieldName()));
                String oldPassword = PasswordEncoder.encodePassword(paramsMap.get(FieldEnum.OLD_PASSWORD.getFieldName()));

                if (userDAO.findUserByLoginAndPassword(userLogin, oldPassword) == null) {
                    throw new ReceiverException("Old password is incorrect ");
                }
                userDAO.changeUserPassword(userId, newPassword);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e);
                throw new ReceiverException("addNewUser command wasn't executed", e);
            }
        }

        return errorMap;
    }


    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#updateUserInfo(java.util.Map)
     */
    @Override
    public Map<String, String> updateUserInfo(Map<String, String> paramsMap) throws ReceiverException {
        if (paramsMap == null || paramsMap.isEmpty()) {
            throw new ReceiverException("Data is empty ");
        }

        HashMap<String, String> errorMap = entityValidator.validateUpdatedUser(paramsMap);

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        if (errorMap.isEmpty()) {
            try {
                userDAO.updateEntity(buildUser(paramsMap));
            } catch (DAOException e) {
                throw new ReceiverException("updateUser command wasn't executed", e);
            }
        }

        return errorMap;
    }

    /* (non-Javadoc)
     * @see by.loiko.library.receiver.UserReceiver#deleteUser(java.lang.String)
     */
    @Override
    public void deleteUser(String id) throws ReceiverException {
        EntityValidator validator = new EntityValidator();
        long genreId;
        if (validator.validateId(id)) {
            genreId = Long.parseLong(id);
        } else {
            throw new ReceiverException("Wrong format ID");
        }

        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            userDAO.deleteEntityById(genreId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
            throw new ReceiverException("deleteGenre command wasn't executed", e);
        }
    }

    /**
     * Builds the user.
     *
     * @param paramsMap the params map
     * @return the user
     */
    private User buildUser(Map<String, String> paramsMap) {
        User user = new User();

        user.setLogin(paramsMap.get(FieldEnum.LOGIN.getFieldName()));
        String encodePass = PasswordEncoder.encodePassword(paramsMap.get(FieldEnum.PASSWORD.getFieldName()));
        user.setPassword(encodePass);
        user.setEmail(paramsMap.get(FieldEnum.EMAIL.getFieldName()));
        user.setFirstName(paramsMap.get(FieldEnum.FIRSTNAME.getFieldName()));
        user.setLastName(paramsMap.get(FieldEnum.LASTNAME.getFieldName()));

        if (paramsMap.get(FieldEnum.ID.getFieldName()) != null) {
            long userId = Long.parseLong(paramsMap.get(FieldEnum.ID.getFieldName()));
            user.setId(userId);
        }

        if (paramsMap.get(FieldEnum.ROLE_ID.getFieldName()) != null) {
            int roleId = Integer.parseInt(paramsMap.get(FieldEnum.ROLE_ID.getFieldName()));
            user.setRoleId(roleId);
        }
        if (paramsMap.get(FieldEnum.STATUS.getFieldName()) != null) {
            int intDeleted = Integer.parseInt(paramsMap.get(FieldEnum.STATUS.getFieldName()));
            boolean isDeleted = (intDeleted != 0);
            user.setIsDeleted(isDeleted);
        }

        return user;
    }
}
