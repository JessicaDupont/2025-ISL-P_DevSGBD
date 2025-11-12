/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.PersonViewModel;
import java.util.List;
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
public class PersonDAOTest {
    
    public PersonDAOTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of load method, of class PersonDAO.
     */
    @org.junit.Test
    public void testLoad() {
        System.out.println("load");
        PersonViewModel vm = null;
        PersonDAO instance = new PersonDAO();
        List<Person> expResult = null;
        List<Person> result = instance.load(vm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class PersonDAO.
     */
    @org.junit.Test
    public void testInsert() {
        System.out.println("insert");
        Person e = null;
        PersonDAO instance = new PersonDAO();
        instance.insert(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class PersonDAO.
     */
    @org.junit.Test
    public void testSelect() {
        System.out.println("select");
        PersonViewModel vm = null;
        PersonDAO instance = new PersonDAO();
        instance.select(vm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PersonDAO.
     */
    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        Person e = null;
        PersonDAO instance = new PersonDAO();
        instance.update(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
