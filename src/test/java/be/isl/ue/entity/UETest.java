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
public class UETest {
    
    private Person coordinator;
    private Section sectionIT;
    private Section sectionManagement;
    private UE ueA;
    private UE ueB;
    private UE ueC;

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

        sectionIT = new Section(
                1,
                "IT",
                "Information Technology Section",
                coordinator,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );
        
        sectionManagement = new Section(
                2,
                "Management",
                "Management Section",
                coordinator,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );

        // Full constructor (9 arguments)
        ueA = new UE(
                1,
                "SGBD",
                "Dev SGBD",
                "Project for DB development",
                100,
                true,
                sectionIT,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        // Full constructor (9 arguments)
        ueB = new UE(
                2,
                "WEB",
                "Dev Web",
                "Project for Web development",
                120,
                true,
                sectionManagement,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );
        
        // Partial constructor (6 arguments)
        ueC = new UE(
                "NETW",
                "Networks",
                "Admin networks course",
                80,
                true,
                sectionIT
        );
    }

    @After
    public void tearDown() {
        coordinator = null;
        sectionIT = null;
        sectionManagement = null;
        ueA = null;
        ueB = null;
        ueC = null;
    }

    @Test
    public void testFullConstructor_CheckValues() {
        assertEquals("SGBD", ueA.getCode());
        assertEquals("Dev SGBD", ueA.getName());
        assertEquals(1, (int) ueA.getId());
        assertEquals(100, (int) ueA.getNumberOfPeriods());
        assertTrue(ueA.getIsDecisive());
        assertSame(sectionIT, ueA.getSection());
        assertNotNull(ueA.getInsertedAt());
    }
    
    @Test
    public void testPartialConstructor_CheckValuesAndNullId() {
        assertEquals("NETW", ueC.getCode());
        assertEquals("Networks", ueC.getName());
        assertNull(ueC.getId());
        assertNull(ueC.getInsertedAt());
    }

    @Test
    public void testSetters_UpdateValues() {
        String newName = "Science";
        String newCode = "Science-C";
        ueA.setName(newName);
        ueA.setCode(newCode);
        ueA.setSection(sectionManagement);
        
        assertEquals(newName, ueA.getName());
        assertEquals(newCode, ueA.getCode());
        assertSame(sectionManagement, ueA.getSection());
    }
    
    @Test
    public void testCompareTo_SameId_ReturnsZero() {
        // Fix: Use the same Name, Code, Description, and Section to force the full comparison chain to return 0.
        UE ueD = new UE(
                1,
                "SGBD",
                "Dev SGBD",
                "Project for DB development",
                50, // Ignored by compareTo
                false, // Ignored by compareTo
                sectionIT,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5)
        );
        assertEquals(0, ueA.compareTo(ueD));
    }
    
    @Test
    public void testCompareTo_DifferentId_AisSmaller() {
        assertTrue(ueA.compareTo(ueB) < 0); 
    }
    
    @Test
    public void testCompareTo_SameIdNullObject_ReturnsPositive() {
        assertTrue(ueA.compareTo(null) > 0);
    }
    
    @Test
    public void testCompareTo_SameNameDifferentCode_CodeDominates() {
        // ID is null, so comparison starts with Name
        UE ueD = new UE(
                "A-Code", "Same Name", "Desc", 100, true, sectionIT
        );
        UE ueE = new UE(
                "Z-Code", "Same Name", "Desc", 100, true, sectionIT
        );
        assertTrue(ueD.compareTo(ueE) < 0); 
    }
    
    @Test
    public void testCompareTo_SameNameSameCodeDifferentDescription_DescriptionDominates() {
        // ID and Name are null, comparison starts with Code, then Description
        UE ueD = new UE(
                "CODE", "Same Name", "A-Desc", 100, true, sectionIT
        );
        UE ueE = new UE(
                "CODE", "Same Name", "Z-Desc", 100, true, sectionIT
        );
        assertTrue(ueD.compareTo(ueE) < 0); 
    }
    
    @Test
    public void testCompareTo_SameNameSameCodeSameDescriptionDifferentSection_SectionDominates() {
        // ID, Name, Code, Description are equal. Comparison goes to Section.
        // Section IT (ID 1) vs Section Management (ID 2). IT < Management by ID comparison inside Section.
        UE ueD = new UE(
                "CODE", "Same Name", "Desc", 100, true, sectionIT
        );
        UE ueE = new UE(
                "CODE", "Same Name", "Desc", 100, true, sectionManagement
        );
        
        assertTrue(ueD.compareTo(ueE) < 0);
    }
}