package by.loiko.library.command;

import by.loiko.library.controller.Router;
import by.loiko.library.receiver.ReceiverFactory;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public interface Command {
    ReceiverFactory factory = ReceiverFactory.getInstance();

    Router execute(HttpServletRequest request);

}
