package be.isl.ue.ui.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UEViewModelTest {

    private UEViewModel instance;

    @Before
    public void setUp() {
        instance = new UEViewModel();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGettersAndSetters() {
        System.out.println("Testing UEViewModel Getters and Setters");
        
        String name = "Développement SGBD";
        String description = "Projet en Java/PostgreSQL";
        String section = "Informatique";
        String capacity = "10 ECTS";

        // Set values
        instance.setName(name);
        instance.setDescription(description);
        instance.setSection(section);
        instance.setCapacity(capacity);

        // Assert Getters
        assertEquals("Name must match.", name, instance.getName());
        assertEquals("Description must match.", description, instance.getDescription());
        assertEquals("Section must match.", section, instance.getSection());
        assertEquals("Capacity must match.", capacity, instance.getCapacity());
    }
}