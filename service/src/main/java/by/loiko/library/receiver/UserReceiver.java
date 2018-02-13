package by.loiko.library.receiver;

import by.loiko.library.entity.User;

import java.util.List;
import java.util.Map;


/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface UserReceiver extends AbstractReceiver {

    /**
     * Find all users (and deleted).
     *
     * @return the list
     * @throws ReceiverException the receiver exception
     */
    List<User> findAllUsers() throws ReceiverException;

    /**
     * Find user.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws ReceiverException the receiver exception
     */
    User findUser(String login, String password) throws ReceiverException;
    
    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws ReceiverException the receiver exception
     */
    User findUserById (String id) throws ReceiverException;
    
    /**
     * Adds the new user.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> AddNewUser(Map<String, String> paramsMap) throws ReceiverException;
    
    /**
     * Change password.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> changePassword(Map<String, String> paramsMap) throws ReceiverException;

    /**
     * Update user info.
     *
     * @param paramsMap the params map
     * @return the map
     * @throws ReceiverException the receiver exception
     */
    Map<String, String> updateUserInfo(Map<String, String> paramsMap) throws ReceiverException;

    /**
     * Change status user to deleted.
     *
     * @param id the id
     * @throws ReceiverException the receiver exception
     */
    void deleteUser(String id) throws ReceiverException;

}
