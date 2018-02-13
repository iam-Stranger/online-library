package by.loiko.library.dao;

import by.loiko.library.entity.Author;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public interface AuthorDAO extends AbstractDAO<Author> {

    /**
     * Find authors by book id.
     *
     * @param id the id
     * @return the list of Authors
     * @throws DAOException the DAO exception
     */
    List<Author> findAuthorsByBookId(long id) throws DAOException;

    /**
     * Find all authors (and deleted too).
     *
     * @return the list of Authors
     * @throws DAOException the DAO exception
     */
    List<Author> findAllAuthorsAbs() throws DAOException;

    /**
     * Find all authors where whose books count > 0 .
     *
     * @return the list of Authors
     * @throws DAOException the DAO exception
     */
    List<Author> findAllNotEmptyAuthors() throws DAOException;
}
