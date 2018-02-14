package by.loiko.library.dao;

import by.loiko.library.entity.Entity;
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

/**
 * @param <T> the generic type
 */
public interface AbstractDAO<T extends Entity> {
    Logger logger = LogManager.getLogger();

    /**
     * Delete Entity by id.
     *
     * @param id the ID of Entity
     * @throws DAOException the DAO exception
     */
    void deleteEntityById(long id) throws DAOException;

    /**
     * Adds the new Entity.
     *
     * @param entity the Entity
     * @throws DAOException the DAO exception
     */
    void addNewEntity(T entity) throws DAOException;

    /**
     * Update Entity.
     *
     * @param entity the Entity
     * @throws DAOException the DAO exception
     */
    void updateEntity(T entity) throws DAOException;

    /**
     * Find Entity by id.
     *
     * @param id the ID of Entity
     * @return the Entity
     * @throws DAOException the DAO exception
     */
    T findEntityById(long id) throws DAOException;

    /**
     * Find all entities.
     *
     * @return the List<Entity>
     * @throws DAOException the DAO exception
     */
    List<T> findAllEntities() throws DAOException;

    /**
     * Find Entities by array of ID.
     *
     * @param idList the id list
     * @return the List<Entity>
     * @throws DAOException the DAO exception
     */
    List<T> findEntitiesByArrayOfId(List<Long> idList) throws DAOException;


    /**
     * Gets connection back to connection pool
     *
     * @param connection the ProxyConnection
     */
    default void releaseConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    /**
     * Close statement.
     *
     * @param statement the Statement
     */
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
