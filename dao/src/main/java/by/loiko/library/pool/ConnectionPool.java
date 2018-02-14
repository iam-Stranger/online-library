package by.loiko.library.pool;

import by.loiko.library.dao.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


/***
 Author: Aliaksei Loika
 Date: 06.12.2017
 ***/
public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static AtomicBoolean isCreatedInstance = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> queue;
    private final static int POOL_SIZE = Integer.parseInt(ConfigurationManager.getProperty("pool.size"));


    /// !!!! UNDER CONSTRUCTION !!!!
    // переделываю, к защите будет красивый новый пулл.


    /**
     * Instantiates a new connection pool.
     */
    private ConnectionPool() {
        if (instance != null) {
            throw new RuntimeException("Reflection operations is not accessible");
        }

        queue = new ArrayBlockingQueue<>(POOL_SIZE);

        try {
            if (initConnectionPool(POOL_SIZE)) {

            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if (!isCreatedInstance.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreatedInstance.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;

        try {
            connection = queue.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Can not get connection from pool: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(ProxyConnection connection) {
        queue.offer(connection);
    }

    /**
     * Inits the connection pool.
     *
     * @param size the size
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    private boolean initConnectionPool(int size) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        for (int i = 0; i < size; i++) {
            try {
                queue.put(ConnectionCreator.getConnection());
            } catch (DAOException | InterruptedException e) {
                logger.log(Level.ERROR, "Connection wasn't created: " + e.getMessage());
            }
        }

        return queue.size() == size;
    }

    /**
     * Destroy connection pool.
     */
    public void destroyConnectionPool() {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            try {
                ProxyConnection connection = queue.take();
                connection.close();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Can't take connection from pool: " + e.getMessage());
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Can't releaseConnection connection: " + e.getMessage());
            }

            try {
                Enumeration<Driver> drivers = DriverManager.getDrivers();
                while (drivers.hasMoreElements()) {
                    DriverManager.deregisterDriver(drivers.nextElement());
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Can't deregister driver: " + e.getMessage());
            }
        }

    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
