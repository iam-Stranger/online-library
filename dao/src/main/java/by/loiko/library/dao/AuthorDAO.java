package by.loiko.library.dao;

import by.loiko.library.entity.Author;
import by.loiko.library.exception.DAOException;

import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 31.01.2018
 ***/
public interface AuthorDAO extends AbstractDAO<Author> {

    List<Author> findAuthorsByBookId(long id) throws DAOException;

    List<Author> findAllAuthorsAbs() throws DAOException;

    List<Author> findAllNotEmptyAuthors() throws DAOException;
}
