/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package be.isl.ue.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jessi
 */
public class Connect2DBTest {
    
    public Connect2DBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        Connect2DB.close();
    }

    @Test
    public void testGetConn_Success() throws SQLException {
        System.out.println("testGetConn_Success: Checking for a valid connection");
        
        Connect2DB instance = new Connect2DB();
        Connection result = instance.getConn();
        
        assertNotNull("The Connection object should not be null after successful initialization.", result);
        
        assertTrue("The Connection should be open (valid).", result.isValid(1));
    }

    @Test
    public void testClose_ConnectionIsClosed() throws SQLException {
        System.out.println("testClose_ConnectionIsClosed: Checking if the connection closes");
        
        Connect2DB instance = new Connect2DB();
        Connection conn = instance.getConn();
        
        assertNotNull(conn);
        assertTrue(conn.isValid(1));
        
        Connect2DB.close();
        
        assertTrue("The Connection object should be closed after calling Connect2DB.close().", conn.isClosed());
    }
    
    @Test
    public void testGetConn_ReturnsSameInstance() {
        System.out.println("testGetConn_ReturnsSameInstance: Checking for Singleton behavior");
        
        Connect2DB instance1 = new Connect2DB();
        Connection conn1 = instance1.getConn();
        
        Connect2DB instance2 = new Connect2DB();
        Connection conn2 = instance2.getConn();
        
        assertSame("Subsequent calls to the constructor should return the same Connection object.", conn1, conn2);
    }
    
}
