package by.loiko.library.command;

import by.loiko.library.command.admin.EditUserInfoCommand;
import by.loiko.library.command.admin.ShowAllUsersCommand;
import by.loiko.library.command.admin.ShowHistoryOrders;
import by.loiko.library.command.admin.ValidateInfoEditUserCommand;
import by.loiko.library.command.common.*;
import by.loiko.library.command.librarian.*;
import by.loiko.library.command.navigate.*;
import by.loiko.library.command.user.ShowBookInfoCommand;
import by.loiko.library.command.user.ShowFindBooksCommand;
import by.loiko.library.command.user.ShowOrderListCommand;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public enum CommandName {
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    GO_TO_PAGE(new GoToPageCommand()),

    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),

    SHOW_ORDER_LIST(new ShowOrderListCommand()),

    EDIT_USER_INFO(new EditUserInfoCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),

    SHOW_HISTORY_ORDERS(new ShowHistoryOrders()),

    SHOW_FIND_BOOKS(new ShowFindBooksCommand()),
    SHOW_BOOK_INFO(new ShowBookInfoCommand()),
    EDIT_BOOK_INFO(new EditBookInfoCommand()),
    SHOW_ALL_BOOKS(new ShowAllBooksCommand()),
    UPDATE_BOOK_INFO(new UpdateBookInfoCommand()),
    ADD_NEW_BOOK(new AddNewBookCommand()),

    DELETE_BOOK(new DeleteBookCommand()),


    EDIT_GENRE_INFO(new EditGenreInfoCommand()),
    SHOW_ALL_GENRES(new ShowAllGenresCommand()),
    UPDATE_GENRE_INFO(new UpdateGenreInfoCommand()),
    DELETE_GENRE(new DeleteGenreCommand()),
    ADD_NEW_GENRE(new AddNewGenreCommand()),


    EDIT_AUTHOR_INFO(new EditAuthorInfoCommand()),
    SHOW_ALL_AUTHORS(new ShowAllAuthorsCommand()),
    UPDATE_AUTHOR_INFO(new UpdateAuthorInfoCommand()),
    DELETE_AUTHOR(new DeleteAuthorCommand()),
    ADD_NEW_AUTHOR(new AddNewAuthorCommand()),

    DIALOG_DELETE(new DialogDeleteCommand()),

    VALIDATE_INFO_EDIT_USER(new ValidateInfoEditUserCommand()),
    VALIDATE_INFO_NEW_USER(new ValidateInfoNewUserCommand()),

    TO_ADD_BOOK_PAGE(new ToAddBookPageCommand()),
    TO_ADD_AUTHOR_PAGE(new ToAddAuthorPageCommand()),
    TO_ADD_GENRE_PAGE(new ToAddGenrePageCommand()),
    TO_FIND_BOOK_PAGE(new ToFindBookPageCommand()),
    TO_SIGN_UP_PAGE(new ToSignUpCommand()),


    WRONG_COMMAND(new WrongCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
