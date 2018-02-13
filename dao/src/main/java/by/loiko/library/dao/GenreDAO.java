package by.loiko.library.dao;

import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;

import java.util.List;


/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public interface GenreDAO extends AbstractDAO<Genre> {

    /**
     * Find genres by book id.
     *
     * @param id the id
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Genre> findGenresByBookId(long id) throws DAOException;

    /**
     * Find all genres (not deleted).
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Genre> findAllGenresAbs() throws DAOException;

    /**
     * Find all not genres which books count > 0
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Genre> findAllNotEmptyGenres() throws DAOException;

}
