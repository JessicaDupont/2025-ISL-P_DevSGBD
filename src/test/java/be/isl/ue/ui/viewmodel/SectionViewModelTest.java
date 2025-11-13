package be.isl.ue.ui.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SectionViewModelTest {

    private SectionViewModel instance;

    @Before
    public void setUp() {
        instance = new SectionViewModel();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGettersAndSetters() {
        System.out.println("Testing SectionViewModel Getters and Setters");
        
        String name = "Informatique de Gestion";
        String coordinatorLastName = "Dupont";

        // Set values
        instance.setName(name);
        instance.setCoordinatorLastName(coordinatorLastName);

        // Assert Getters
        assertEquals("Name must match.", name, instance.getName());
        assertEquals("Coordinator last name must match.", coordinatorLastName, instance.getCoordinatorLastName());
    }
}