package by.loiko.library.receiver;

import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;

import java.util.ArrayList;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface UserReceiver extends AbstractReceiver {

    ArrayList<User> findAllUsers() throws ReceiverException;

    User findUser(String login, String password) throws ReceiverException;
    User findUserById (long id) throws ReceiverException;
    Map<String, String> AddNewUser(Map<String, String> paramsMap) throws ReceiverException;

    boolean signIn(String login, String password) throws ReceiverException;

}
