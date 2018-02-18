package by.loiko.library.listener;

import by.loiko.library.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    /**
    * When ServletContext is destroyed:
    * Close all connection with DB
    * Deregister all DriverManager drivers
    */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroyConnectionPool();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

}
