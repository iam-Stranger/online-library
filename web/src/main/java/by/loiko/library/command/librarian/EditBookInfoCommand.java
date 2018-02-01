package by.loiko.library.command.librarian;

import by.loiko.library.command.Command;
import by.loiko.library.command.PageConstant;
import by.loiko.library.controller.Router;
import by.loiko.library.entity.Author;
import by.loiko.library.entity.Book;
import by.loiko.library.entity.Genre;
import by.loiko.library.exception.ReceiverException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 30.01.2018
 ***/
public class EditBookInfoCommand implements Command {
    private static final String MESSAGE_PARAM = "message";
    private static final String BOOK_ID_PARAM = "id";
    private static final String BOOK_PARAM = "book";
    private static final String GENRES_PARAM = "genres";
    private static final String AUTHORS_PARAM = "authors";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long authorId;
        try {
            authorId = Long.valueOf(request.getParameter(BOOK_ID_PARAM));
        } catch (NumberFormatException e) {
            authorId = 0;
        }

        try {
            List<Genre> genreList = factory.getBookReceiver().findAllGenres();
            List<Author> authorList = factory.getBookReceiver().findAllAuthors();
            Book book = factory.getBookReceiver().findBookById(authorId);

            request.setAttribute(AUTHORS_PARAM, authorList);
            request.setAttribute(GENRES_PARAM, genreList);
            request.setAttribute(BOOK_PARAM, book);
            router.setPagePath(PageConstant.EDIT_BOOK_FORM);

        } catch (ReceiverException e) {

            request.setAttribute(MESSAGE_PARAM, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
        }

        request.getSession().setAttribute("url", request.getRequestURI() + "?" + request.getQueryString());
        return router;
    }
}
