package by.loiko.library.command;

import by.loiko.library.command.admin.ShowAllUsers;
import by.loiko.library.command.common.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();

    private final HashMap<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandName.GO_TO_PAGE, new GoToPage());
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsers());
        commands.put(CommandName.WRONG_COMMAND, new WrongCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        String name = request.getParameter("command");
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {  //??
            commandName = CommandName.WRONG_COMMAND;
        }

        return commands.get(commandName);
    }

}
