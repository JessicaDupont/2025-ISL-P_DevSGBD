package be.isl.ue.dao.table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for PersonTable, ensuring all table and column names constants
 * are correctly defined for the database schema.
 */
public class PersonTableTest {

    private PersonTable instance;

    @Before
    public void setUp() {
        instance = new PersonTable();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testTableAndIdColumns() {
        System.out.println("Testing inherited table and ID columns");
        
        // Tests des propriétés héritées de AbstractTable
        assertEquals("Table name must be 'person'.", "person", instance.TABLE_NAME);
        assertEquals("ID column must be 'person_id'.", "person_id", instance.COLUMN_ID);
        assertEquals("Inserted at column must be 'inserted_ts'.", "inserted_ts", instance.INSERTED_AT);
        assertEquals("Updated at column must be 'updated_ts'.", "updated_ts", instance.UPDATED_AT);
    }

    @Test
    public void testPersonColumns() {
        System.out.println("Testing specific Person column names (snake_case)");
        
        // Tests des colonnes spécifiques à PersonTable
        assertEquals("First name column must be 'first_name'.", "first_name", instance.FIRSTNAME);
        assertEquals("Last name column must be 'last_name'.", "last_name", instance.LASTNAME);
        assertEquals("Date of birth column must be 'date_of_birth'.", "date_of_birth", instance.DATE_OF_BIRTH);
        assertEquals("Email column must be 'email'.", "email", instance.EMAIL);
        assertEquals("Mobile column must be 'mobile'.", "mobile", instance.MOBILE);
        assertEquals("Address column must be 'address'.", "address", instance.ADDRESS);
        assertEquals("Postal code column must be 'postal_code'.", "postal_code", instance.POSTAL_CODE);
        assertEquals("City column must be 'city'.", "city", instance.CITY);
        assertEquals("Country column must be 'country'.", "country", instance.COUNTRY);
        assertEquals("Is jury member column must be 'is_jury_member'.", "is_jury_member", instance.IS_JURY_MEMBER);
        assertEquals("Is teacher column must be 'is_teacher'.", "is_teacher", instance.IS_TEACHER);
    }
}