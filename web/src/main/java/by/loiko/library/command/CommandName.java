package by.loiko.library.command;

import by.loiko.library.command.admin.EditUserInfoCommand;
import by.loiko.library.command.admin.ShowAllUsersCommand;
import by.loiko.library.command.admin.ShowHistoryOrders;
import by.loiko.library.command.admin.ValidateInfoEditUserCommand;
import by.loiko.library.command.common.*;
import by.loiko.library.command.librarian.ShowAllAuthorsCommand;
import by.loiko.library.command.librarian.ShowAllGenresCommand;
import by.loiko.library.command.navigate.ToFindBookPageCommand;
import by.loiko.library.command.user.ShowOrderListCommand;
import by.loiko.library.command.user.ShowBookInfoCommand;
import by.loiko.library.command.user.ShowFindBooksCommand;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public enum CommandName {
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    GO_TO_PAGE(new GoToPageCommand()),

    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),
    SIGN_UP(new SingnUpCommand()),

    SHOW_BOOK_INFO(new ShowBookInfoCommand()),
    SHOW_FIND_BOOKS(new ShowFindBooksCommand()),

    SHOW_ORDER_LIST(new ShowOrderListCommand()),

    EDIT_USER_INFO(new EditUserInfoCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    SHOW_HISTORY_ORDERS(new ShowHistoryOrders()),

    SHOW_ALL_GENRES(new ShowAllGenresCommand()),
    SHOW_ALL_AUTHORS(new ShowAllAuthorsCommand()),

    VALIDATE_INFO_EDIT_USER(new ValidateInfoEditUserCommand()),
    VALIDATE_INFO_NEW_USER(new ValidateInfoNewUserCommand()),

    WRONG_COMMAND(new WrongCommand()),

    TO_FIND_BOOK_PAGE(new ToFindBookPageCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
