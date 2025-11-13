/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package be.isl.ue.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class SectionTest {
    
    private Person coordinatorA;
    private Person coordinatorB;
    private Section sectionA;
    private Section sectionB;
    private Section sectionC;

    @Before
    public void setUp() {
        coordinatorA = new Person(
                10,
                "Marie",
                "Curie",
                LocalDate.of(1867, 11, 7),
                "m.curie@test.com",
                "111",
                "addressA",
                "1000",
                "CityA",
                "CountryA",
                true,
                false,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5)
        );

        coordinatorB = new Person(
                20,
                "Albert",
                "Einstein",
                LocalDate.of(1879, 3, 14),
                "a.einstein@test.com",
                "222",
                "addressB",
                "2000",
                "Brussels",
                "Belgium",
                false,
                true,
                LocalDateTime.now().minusDays(20),
                LocalDateTime.now().minusDays(10)
        );

        sectionA = new Section(
                1,
                "IT",
                "Information Technology Section",
                coordinatorA,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        sectionB = new Section(
                2,
                "Management",
                "Management Section",
                coordinatorB,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );
        
        sectionC = new Section(
                "Finance",
                "Finance Section (Partial)",
                coordinatorA
        );
    }

    @After
    public void tearDown() {
        coordinatorA = null;
        coordinatorB = null;
        sectionA = null;
        sectionB = null;
        sectionC = null;
    }

    @Test
    public void testFullConstructor_CheckValues() {
        assertEquals("IT", sectionA.getName());
        assertEquals("Information Technology Section", sectionA.getDescription());
        assertEquals(1, (int) sectionA.getId());
        assertSame(coordinatorA, sectionA.getCoordinator());
    }
    
    @Test
    public void testPartialConstructor_CheckValuesAndNullId() {
        assertEquals("Finance", sectionC.getName());
        assertEquals("Finance Section (Partial)", sectionC.getDescription());
        assertNull(sectionC.getId());
        assertSame(coordinatorA, sectionC.getCoordinator());
        assertNull(sectionC.getInsertedAt());
    }

    @Test
    public void testSetters_UpdateValues() {
        String newName = "Science";
        String newDescription = "New description";
        sectionA.setName(newName);
        sectionA.setDescription(newDescription);
        sectionA.setCoordinator(coordinatorB);
        
        assertEquals(newName, sectionA.getName());
        assertEquals(newDescription, sectionA.getDescription());
        assertSame(coordinatorB, sectionA.getCoordinator());
    }
    
    @Test
    public void testCompareTo_SameId_ReturnsZero() {
        // Correction: We must use coordinatorA so that the coordinator.lastName comparison returns 0
        Section sectionD = new Section(
                1,
                "IT",
                "Different description",
                coordinatorA, // The fix: Use the same coordinator (Curie)
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5)
        );
        assertEquals(0, sectionA.compareTo(sectionD));
    }
    
    @Test
    public void testCompareTo_DifferentId_IdDominates() {
        assertTrue(sectionA.compareTo(sectionB) < 0); 
    }
    
    @Test
    public void testCompareTo_SameIdDifferentName_NameDominates() {
        Section sectionD = new Section(
                1, "IT", "Description", coordinatorA, null, null
        );
        Section sectionE = new Section(
                1, "Z-Section", "Description", coordinatorA, null, null
        );
        
        assertTrue(sectionD.compareTo(sectionE) < 0); 
    }
    
    @Test
    public void testCompareTo_SameNameDifferentCoordinator_LastNameDominates() {
        Section sectionD = new Section(
                "SameName", "Description", coordinatorA
        );
        Section sectionE = new Section(
                "SameName", "Description", coordinatorB
        );
        
        assertTrue(sectionD.compareTo(sectionE) < 0);
    }
    
    @Test
    public void testCompareTo_NullCoordinator_ReturnsCorrectOrder() {
        Section sectionD = new Section(
                "SameName", "Description", null
        );
        Section sectionE = new Section(
                "SameName", "Description", coordinatorB
        );
        
        assertTrue(sectionD.compareTo(sectionE) > 0); 
    }
    
    @Test
    public void testCompareTo_NullObject_ReturnsPositive() {
        assertTrue(sectionA.compareTo(null) > 0);
    }
}