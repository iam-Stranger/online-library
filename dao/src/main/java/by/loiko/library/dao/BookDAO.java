package by.loiko.library.dao;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface BookDAO extends AbstractDAO<Book> {

    List<Book> findBooksByTitle(String title) throws DAOException;

    List<Book> findAllBooks() throws DAOException;

    List<Book> findBooksByGenreId(long genreId) throws DAOException;

    List<Book> findBooksByAuthorId(long authorId) throws DAOException;


    default String createINExpression(List<Long> idList) {
        StringBuilder expression = new StringBuilder().append(" AND id IN (");
        for (int i = 0; i < idList.size() - 1; i++) {
            expression.append("?, ");
        }
        expression.append("?)");

        return expression.toString();
    }

}
