package by.loiko.library.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class CommandProvider {
    private static Logger logger = LogManager.getLogger();

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        return getCommandName(request).getCommand();
    }

    public int getCommandAccessLevel(HttpServletRequest request){
        return getCommandName(request).getAccessLevel();
    }

    public CommandName getCommandName(HttpServletRequest request) {
        String name = request.getParameter(ParamConstant.COMMAND_PARAM);
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.log(Level.DEBUG,"Unknown command: "+name);
            commandName = CommandName.WRONG_COMMAND;
        }
        return commandName;
    }


}
