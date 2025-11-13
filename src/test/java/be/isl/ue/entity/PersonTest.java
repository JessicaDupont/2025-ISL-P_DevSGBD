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
public class PersonTest {
    
    private Person personA;
    private Person personB;
    private Person personC;

    @Before
    public void setUp() {
        personA = new Person(
                1,
                "Jessica",
                "Dupont",
                LocalDate.of(1990, 3, 12),
                "j.dupont@test.com",
                "0478123456",
                "Rue du Test 1",
                "4577",
                "Marchin",
                "Belgium",
                true,
                false,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );
        
        personB = new Person(
                2,
                "Albert",
                "Einstein",
                LocalDate.of(1879, 3, 14),
                "a.einstein@test.com",
                "0478654321",
                "Street 2",
                "1000",
                "Brussels",
                "Belgium",
                false,
                true,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );
        
        personC = new Person(
                "Jessica",
                "Dupont",
                LocalDate.of(1990, 3, 12),
                "j.dupont@test.com",
                "0478123456",
                "Rue du Test 1",
                "4577",
                "Marchin",
                "Belgium",
                true,
                false
        );
    }

    @After
    public void tearDown() {
        personA = null;
        personB = null;
        personC = null;
    }

    @Test
    public void testFullConstructor_CheckValues() {
        assertEquals("Jessica", personA.getFirstName());
        assertEquals("Dupont", personA.getLastName());
        assertEquals(1, (int) personA.getId());
        assertTrue(personA.getIsJuryMember());
        assertFalse(personA.getIsTeacher());
        assertNotNull(personA.getInsertedAt());
    }
    
    @Test
    public void testPartialConstructor_CheckValuesAndNullId() {
        assertEquals("Jessica", personC.getFirstName());
        assertEquals("Dupont", personC.getLastName());
        assertNull(personC.getId());
        assertNull(personC.getInsertedAt());
    }

    @Test
    public void testSetFirstName_UpdateValue() {
        String newName = "Jessie";
        personA.setFirstName(newName);
        assertEquals(newName, personA.getFirstName());
    }
    
    @Test
    public void testSetLastName_UpdateValue() {
        String newName = "Dupond";
        personA.setLastName(newName);
        assertEquals(newName, personA.getLastName());
    }

    @Test
    public void testSetDateOfBirth_UpdateValue() {
        LocalDate newDate = LocalDate.of(1991, 1, 1);
        personA.setDateOfBirth(newDate);
        assertEquals(newDate, personA.getDateOfBirth());
    }
    
    @Test
    public void testSetIsJuryMember_UpdateValue() {
        personA.setIsJuryMember(false);
        assertFalse(personA.getIsJuryMember());
    }

    @Test
    public void testSetIsTeacher_UpdateValue() {
        personA.setIsTeacher(true);
        assertTrue(personA.getIsTeacher());
    }

    @Test
    public void testCompareTo_SameId_ReturnsZero() {
        // Test corrigé : création d'un clone parfait de personA. 
        // ID et tous les champs secondaires sont identiques, garantissant un retour de 0.
        Person personD = new Person(
            personA.getId(),
            personA.getFirstName(), personA.getLastName(), personA.getDateOfBirth(),
            personA.getEmail(), personA.getMobile(),
            personA.getAddress(), personA.getPostalCode(), personA.getCity(), personA.getCountry(),
            personA.getIsJuryMember(), personA.getIsTeacher(),
            personA.getInsertedAt(), personA.getUpdatedAt()
        );
        
        assertEquals(0, personA.compareTo(personD));
    }
    
    @Test
    public void testCompareTo_DifferentId_AisSmaller() {
        assertTrue(personA.compareTo(personB) < 0); 
    }
    
    @Test
    public void testCompareTo_DifferentId_BisGreater() {
        assertTrue(personB.compareTo(personA) > 0);
    }
    
    @Test
    public void testCompareTo_NullObject_ReturnsPositive() {
        assertTrue(personA.compareTo(null) > 0);
    }
    
    @Test
    public void testCompareTo_SameContentNoId_ComparesByFirstName() {
        Person personD = new Person(
            "Albert",
            "Dupont",
            LocalDate.of(1990, 3, 12),
            "j.dupont@test.com",
            "0478123456",
            "Rue du Test 1",
            "4577",
            "Marchin",
            "Belgium",
            true,
            false
        );
        
        assertTrue(personC.compareTo(personD) > 0);
    }
}