package by.loiko.library.command;

import by.loiko.library.command.admin.*;
import by.loiko.library.command.common.*;
import by.loiko.library.command.librarian.*;
import by.loiko.library.command.navigate.*;
import by.loiko.library.command.user.*;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/

/**
 * COMMAND_NAME ( the command object , access level of command)
 */
public enum CommandName {
    CHANGE_LOCALE(new ChangeLocaleCommand(),1),
    GO_TO_PAGE(new GoToPageCommand(),1),

    SIGN_IN(new SignInCommand(),1),
    SIGN_OUT(new SignOutCommand(),1),
    CHANGE_USER_PASSWORD(new ChangeUserPasswordCommand(),1),

    SHOW_CURRENT_ORDER_LIST(new ShowCurrentOrderListCommand(),1),
    SHOW_ACTIVE_USER_ORDERS(new ShowActiveUserOrdersCommand(),1),

    EDIT_USER_INFO(new EditUserInfoCommand(),3),
    SHOW_ALL_USERS(new ShowAllUsersCommand(),3),
    DELETE_USER(new DeleteUserCommand(),3),

    SHOW_HISTORY_ORDERS(new ShowHistoryOrders(),3),

    MANAGE_ORDERS(new ManageOrdersCommand(),2),
    GIVE_BOOK_TO_USER(new GiveBookToUserCommand(),2),
    DISMISS_BOOK_TO_USER(new DismissBookToUserCommand(),2),
    TAKE_BOOK_FROM_USER(new TakeBookFromUserCommand(),2),
    CANCEL_BOOK_TO_USER(new CancelBookToUserCommand(),1),
    CREATE_ORDER(new CreateOrderCommand(),1),

    SHOW_FIND_BOOKS(new ShowFindBooksCommand(),1),
    SHOW_FIND_BOOKS_BY_GENRE(new ShowFindBooksByGenreCommand(), 1),
    SHOW_FIND_BOOKS_BY_AUTHOR(new ShowFindBooksByAuthorCommand(),1),
    SHOW_BOOK_INFO(new ShowBookInfoCommand(),1),
    SHOW_AVAILABLE_BOOKS(new ShowAvailableBooksCommand(), 1),
    SHOW_AVAILABLE_GENRES(new ShowAvailableGenresCommand(), 1),
    SHOW_AVAILABLE_AUTHORS(new ShowAvailableAuthorsCommand(), 1),

    EDIT_BOOK_INFO(new EditBookInfoCommand(),2),
    SHOW_ALL_BOOKS(new ShowAllBooksCommand(),2),
    UPDATE_BOOK_INFO(new UpdateBookInfoCommand(),2),
    ADD_NEW_BOOK(new AddNewBookCommand(),2),
    DELETE_BOOK(new DeleteBookCommand(),2),


    EDIT_GENRE_INFO(new EditGenreInfoCommand(),2),
    SHOW_ALL_GENRES(new ShowAllGenresCommand(),2),
    UPDATE_GENRE_INFO(new UpdateGenreInfoCommand(),2),
    DELETE_GENRE(new DeleteGenreCommand(),2),
    ADD_NEW_GENRE(new AddNewGenreCommand(),2),


    EDIT_AUTHOR_INFO(new EditAuthorInfoCommand(),2),
    SHOW_ALL_AUTHORS(new ShowAllAuthorsCommand(),2),
    UPDATE_AUTHOR_INFO(new UpdateAuthorInfoCommand(),2),
    DELETE_AUTHOR(new DeleteAuthorCommand(),2),
    ADD_NEW_AUTHOR(new AddNewAuthorCommand(),2),

    DIALOG_DELETE(new DialogDeleteCommand(),2),
    DIALOG_GIVE_BOOK(new DialogGiveBookCommand(),2),
    DIALOG_DISMISS_BOOK(new DialogDismissBookCommand(),2),
    DIALOG_TAKE_BOOK(new DialogTakeBookCommand(),2),
    DIALOG_CANCEL_BOOK(new DialogCancelBookCommand(),1),

    UPDATE_USER_INFO(new UpdateUserInfoCommand(),3),
    ADD_NEW_USER(new AddNewUserCommand(),1),

    TO_ADD_BOOK_PAGE(new ToAddBookPageCommand(),2),
    TO_ADD_AUTHOR_PAGE(new ToAddAuthorPageCommand(),2),
    TO_ADD_GENRE_PAGE(new ToAddGenrePageCommand(),2),
    TO_FIND_BOOK_PAGE(new ToFindBookPageCommand(),1),
    TO_SIGN_UP_PAGE(new ToSignUpCommand(),1),
    TO_MAIN_PAGE(new ToMainPageCommand(),1),
    TO_SIGN_IN_PAGE(new ToSignInCommand(),1),
    TO_CHANGE_PASSWORD(new ToCangePasswordCommand(),1),


    WRONG_COMMAND(new WrongCommand(),1);

    private Command command;
    private int accessLevel;

    CommandName(Command command, int accessLevel) {
        this.command = command;
        this.accessLevel = accessLevel;
    }

    public Command getCommand() {
        return command;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
