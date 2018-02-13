package by.loiko.library.dao;

import by.loiko.library.entity.Entity;
import by.loiko.library.exception.DAOException;
import by.loiko.library.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public interface AbstractDAO<T extends Entity> {
    static Logger logger = LogManager.getLogger();

    void deleteEntityById(long id) throws DAOException;
    void addNewEntity(T entity) throws DAOException;
    void updateEntity(T entity) throws DAOException;

    T findEntityById(long id) throws DAOException;
    List<T> findAllEntities() throws DAOException;
    List<T> findEntitiesByArrayOfId(List<Long> idList) throws DAOException;


    /*Gets connection back to connection pool*/
    default void releaseConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

}
