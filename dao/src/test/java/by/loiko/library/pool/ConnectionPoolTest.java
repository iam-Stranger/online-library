/*
package by.loiko.library.pool;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

*/
/***
 Author: Aliaksei Loika
 Date: 13.02.2018
 ***//*

public class ConnectionPoolTest {

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void getConnectionTest() throws Exception {
        ConnectionPool.getInstance().getConnection();
    }

    @Test
    public void testGetConnection() throws Exception {
    }

    @Test
    public void testReleaseConnection() throws Exception {
    }

    @Test
    public void testDestroyConnectionPool() throws Exception {
    }

    @Test(expectedExceptions = CloneNotSupportedException.class)
    public void testClone() throws Exception {
        ConnectionPool.getInstance().clone();
    }
}*/
