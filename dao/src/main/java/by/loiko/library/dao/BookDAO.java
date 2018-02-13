package by.loiko.library.dao;

import by.loiko.library.entity.Book;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface BookDAO extends AbstractDAO<Book> {

    /**
     * Find books by title.
     *
     * @param title the title
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Book> findBooksByTitle(String title) throws DAOException;

    /**
     * Find all books (not deleted)
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Book> findAllBooks() throws DAOException;

    /**
     * Find books by genre id.
     *
     * @param genreId the genre id
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Book> findBooksByGenreId(long genreId) throws DAOException;

    /**
     * Find books by author id.
     *
     * @param authorId the author id
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Book> findBooksByAuthorId(long authorId) throws DAOException;


    /**
     * Creates SQL query with variable amount wildcards (?)
     *
     * @param idList the id list
     * @return the string
     */
    default String createINExpression(List<Long> idList) {
        StringBuilder expression = new StringBuilder().append(" AND id IN (");
        for (int i = 0; i < idList.size() - 1; i++) {
            expression.append("?, ");
        }
        expression.append("?)");

        return expression.toString();
    }

}
