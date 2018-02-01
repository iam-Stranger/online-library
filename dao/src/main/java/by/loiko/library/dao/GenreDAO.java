package by.loiko.library.dao;

import by.loiko.library.entity.Genre;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public interface GenreDAO extends AbstractDAO<Genre> {

    List<Genre> findGenresByBookId(long id) throws DAOException;

}
