package by.loiko.library.command;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        String name = request.getParameter("command");
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage()); /// LOG
            commandName = CommandName.WRONG_COMMAND;
        }

        return commandName.getCommand();
    }

}
