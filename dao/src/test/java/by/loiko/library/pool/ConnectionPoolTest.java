package by.loiko.library.pool;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ConnectionPoolTest {

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void testGetConnection() throws Exception {
        ConnectionPool.getInstance().getConnection();
    }

    @Test
    public void testInitConnection() throws Exception {
        int actual = ConnectionPool.getInstance().getPoolSize();
        int expected = 49;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDestroyConnectionPool() throws Exception {
    }

    @Test(expectedExceptions = CloneNotSupportedException.class)
    public void testClone() throws Exception {
        ConnectionPool.getInstance().clone();
    }
}
