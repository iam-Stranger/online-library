package by.loiko.library.receiver;

import by.loiko.library.entity.User;
import by.loiko.library.exception.ReceiverException;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface UserReceiver {

    ArrayList<User> findAllUsers() throws ReceiverException;

    User findUser(String login, String password) throws ReceiverException;

    boolean signIn(String login, String password) throws ReceiverException;

}
