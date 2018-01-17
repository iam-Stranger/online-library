package by.loiko.library.dao;

import by.loiko.library.entity.Entity;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ProxyConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface AbstractDAO<T extends Entity> {
    // Logger ???


    ArrayList<T> findAll() throws DAOException;

    T findEntityById() throws DAOException;

    void deleteEntityById(long id) throws DAOException;


    /*Gets connection back to connection pool*/
    default void releaseConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // log
        }
    }

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // log
        }
    }

}
