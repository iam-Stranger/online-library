package by.loiko.library.dao;

import by.loiko.library.pool.ConnectionPool;
import by.loiko.library.pool.ProxyConnection;

import java.sql.SQLException;

/***
 Author: Aliaksei Loika
 Date: 27.12.2017
 ***/
public class TransactionHelper {
    private ProxyConnection connection = ConnectionPool.getInstance().getConnection();

    public void beginTransaction(AbstractDAO ... daos){

        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            // log // exception?
        }

        for (AbstractDAO d : daos){
            //d.
        }

    }
}
