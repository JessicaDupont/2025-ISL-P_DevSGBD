package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.PersonTable;
import be.isl.ue.entity.Person;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for PersonMapper, checking if ResultSet data is correctly mapped to a Person entity.
 */
public class PersonMapperTest {

    private PersonMapper instance;
    private PersonTable table;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        table = new PersonTable();
        instance = new PersonMapper(table);
        
        // Crée un objet Mock (simulation) du ResultSet
        mockResultSet = mock(ResultSet.class);
        
        // Définition des données à simuler
        Integer id = 42;
        String firstName = "JESSICA";
        String lastName = "DUPONT";
        LocalDate dateOfBirth = LocalDate.of(1990, 3, 12);
        String email = "j.dupont@test.be";
        String mobile = "0475123456";
        String address = "Rue de la Source, 1";
        String postalCode = "4577";
        String city = "Marchin";
        String country = "Belgium";
        Boolean isJury = true;
        Boolean isTeacher = false;
        LocalDateTime insertedAt = LocalDateTime.of(2025, 11, 10, 10, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 11, 13, 14, 0, 0);

        // Configuration du comportement du MockResultSet
        when(mockResultSet.getInt(table.COLUMN_ID)).thenReturn(id);
        when(mockResultSet.getString(table.FIRSTNAME)).thenReturn(firstName);
        when(mockResultSet.getString(table.LASTNAME)).thenReturn(lastName);
        when(mockResultSet.getDate(table.DATE_OF_BIRTH)).thenReturn(Date.valueOf(dateOfBirth));
        when(mockResultSet.getString(table.EMAIL)).thenReturn(email);
        when(mockResultSet.getString(table.MOBILE)).thenReturn(mobile);
        when(mockResultSet.getString(table.ADDRESS)).thenReturn(address);
        when(mockResultSet.getString(table.POSTAL_CODE)).thenReturn(postalCode);
        when(mockResultSet.getString(table.CITY)).thenReturn(city);
        when(mockResultSet.getString(table.COUNTRY)).thenReturn(country);
        when(mockResultSet.getBoolean(table.IS_JURY_MEMBER)).thenReturn(isJury);
        when(mockResultSet.getBoolean(table.IS_TEACHER)).thenReturn(isTeacher);
        when(mockResultSet.getTimestamp(table.INSERTED_AT)).thenReturn(Timestamp.valueOf(insertedAt));
        when(mockResultSet.getTimestamp(table.UPDATED_AT)).thenReturn(Timestamp.valueOf(updatedAt));
    }

    @After
    public void tearDown() {
        instance = null;
        table = null;
        mockResultSet = null;
    }

    /**
     * Test of map method, of class PersonMapper.
     */
    @Test
    public void testMap_ValidResultSet_ReturnsCorrectPerson() {
        System.out.println("Testing PersonMapper: map(ResultSet)");
        
        Person result = instance.map(mockResultSet);
        
        assertNotNull("The mapped Person object should not be null.", result);
        
        // Verification des valeurs
        assertEquals("ID must match.", Integer.valueOf(42), result.getId());
        assertEquals("First name must match.", "JESSICA", result.getFirstName());
        assertEquals("Last name must match.", "DUPONT", result.getLastName());
        assertEquals("Date of Birth must match.", LocalDate.of(1990, 3, 12), result.getDateOfBirth());
        assertEquals("Email must match.", "j.dupont@test.be", result.getEmail());
        assertEquals("Mobile must match.", "0475123456", result.getMobile());
        assertEquals("City must match.", "Marchin", result.getCity());
        assertTrue("Is Jury Member must be true.", result.getIsJuryMember());
        assertFalse("Is Teacher must be false.", result.getIsTeacher());
        assertEquals("InsertedAt must match.", LocalDateTime.of(2025, 11, 10, 10, 0, 0), result.getInsertedAt());
        assertEquals("UpdatedAt must match.", LocalDateTime.of(2025, 11, 13, 14, 0, 0), result.getUpdatedAt());
        
        // Vérifie qu'aucune exception n'est lancée par PersonMapper lors de l'appel
        // (La gestion d'erreur dans PersonMapper.map est rudimentaire, il faut s'assurer qu'elle ne cache pas un échec silencieux).
    }
}