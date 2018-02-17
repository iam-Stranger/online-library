package by.loiko.library.pool;

import by.loiko.library.dao.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/***
 Author: Aliaksei Loika
 Date: 07.12.2017
 ***/
class ConnectionCreator {

    private final static String DB_USER = ConfigurationManager.getProperty("db.user");

    private final static String DB_PASSWORD = ConfigurationManager.getProperty("db.password");

    private final static String DB_URL = ConfigurationManager.getProperty("db.url");

    /**
     * Creates new connection and returns ProxyConnection object
     *
     * @return the Proxyconnection
     * @throws DAOException the DAO exception
     */
    static ProxyConnection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return new ProxyConnection(connection);
    }


}
