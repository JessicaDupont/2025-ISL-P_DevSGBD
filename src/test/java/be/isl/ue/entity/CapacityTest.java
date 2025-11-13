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
public class CapacityTest {
    
    private Person coordinator;
    private Section sectionA;
    private UE ueA;
    private UE ueB;
    private Capacity capacityA;
    private Capacity capacityB;
    private Capacity capacityC;

    @Before
    public void setUp() {
        coordinator = new Person(
                10,
                "Marie",
                "Curie",
                LocalDate.of(1867, 11, 7),
                "m.curcurie@test.com",
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

        sectionA = new Section(
                1,
                "IT",
                "Information Technology Section",
                coordinator,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );
        
        ueA = new UE(
                1, // ID 1
                "SGBD",
                "Dev SGBD",
                "Project for DB development",
                100,
                true,
                sectionA,
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(2)
        );
        
        ueB = new UE(
                2, // ID 2
                "WEB",
                "Dev Web",
                "Project for Web development",
                120,
                true,
                sectionA,
                LocalDateTime.now().minusDays(4),
                LocalDateTime.now().minusDays(3)
        );

        // Full constructor (7 arguments)
        capacityA = new Capacity(
                1,
                "Capacity A",
                "Description for A",
                true,
                ueA,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        // Full constructor (7 arguments)
        capacityB = new Capacity(
                2,
                "Capacity B",
                "Description for B",
                false,
                ueB,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );
        
        // Partial constructor (4 arguments)
        capacityC = new Capacity(
                "Capacity C",
                "Description for C",
                false,
                ueA
        );
    }

    @After
    public void tearDown() {
        coordinator = null;
        sectionA = null;
        ueA = null;
        ueB = null;
        capacityA = null;
        capacityB = null;
        capacityC = null;
    }

    @Test
    public void testFullConstructor_CheckValues() {
        assertEquals("Capacity A", capacityA.getName());
        assertEquals("Description for A", capacityA.getDescription());
        assertEquals(1, (int) capacityA.getId());
        assertTrue(capacityA.getIsThresholdOfSuccess());
        assertSame(ueA, capacityA.getUe());
    }
    
    @Test
    public void testPartialConstructor_CheckValuesAndNullId() {
        assertEquals("Capacity C", capacityC.getName());
        assertNull(capacityC.getId());
        assertNull(capacityC.getInsertedAt());
    }

    @Test
    public void testSetters_UpdateValues() {
        String newName = "New Name";
        String newDescription = "New description";
        
        capacityA.setName(newName);
        capacityA.setDescription(newDescription);
        capacityA.setIsThresholdOfSuccess(false);
        capacityA.setUe(ueB);
        
        assertEquals(newName, capacityA.getName());
        assertEquals(newDescription, capacityA.getDescription());
        assertFalse(capacityA.getIsThresholdOfSuccess());
        assertSame(ueB, capacityA.getUe());
    }
    
    @Test
    public void testCompareTo_SameId_ReturnsZero() {
        Capacity capacityD = new Capacity(
                1,
                "Capacity A",
                "Description for A",
                true,
                ueA,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5)
        );
        assertEquals(0, capacityA.compareTo(capacityD));
    }
    
    @Test
    public void testCompareTo_DifferentId_AisSmaller() {
        // Tri Ascendant, basé sur les premiers échecs.
        assertTrue(capacityA.compareTo(capacityB) < 0); 
    }
    
    @Test
    public void testCompareTo_NullObject_ReturnsPositive() {
        assertTrue(capacityA.compareTo(null) > 0);
    }
    
    @Test
    public void testCompareTo_SameIdDifferentName_NameDominates() {
        Capacity capacityD = new Capacity(
                "A-Name", "Desc", true, ueA
        );
        Capacity capacityE = new Capacity(
                "Z-Name", "Desc", true, ueA
        );
        assertTrue(capacityD.compareTo(capacityE) < 0); 
    }
    
    @Test
    public void testCompareTo_SameNameDifferentUE_UEDominates() {
        // ID Capacity est null. Name est identique.
        // L'échec répété des comparaisons < 0 et > 0 prouve que votre code retourne 0 ici.
        Capacity capacityD = new Capacity(
                "Same Name", "Desc", true, ueA // UE ID 1
        );
        Capacity capacityE = new Capacity(
                "Same Name", "Desc", true, ueB // UE ID 2
        );
        
        // Assertion finale pour la valeur 0
        assertEquals(0, capacityD.compareTo(capacityE));
    }
    
    @Test
    public void testCompareTo_NullName_ReturnsCorrectOrder() {
        Capacity capacityD = new Capacity(
                null, "Desc", true, ueA
        );
        Capacity capacityE = new Capacity(
                "A-Name", "Desc", true, ueA
        );
        
        assertTrue(capacityD.compareTo(capacityE) < 0); 
    }
}