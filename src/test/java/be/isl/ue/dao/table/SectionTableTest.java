package be.isl.ue.dao.table;

import be.isl.ue.entity.Section;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for SectionTable.
 */
public class SectionTableTest {
    
    // Instance of the class under test
    private SectionTable table;
    
    @Before
    public void setUp() {
        // Assuming SectionTable is initialized similarly to other tables
        table = new SectionTable(); 
    }

    @Test
    public void testConstants() {
        System.out.println("Testing Constants");
        // These constants tests are inferred to pass and ensure basic setup is correct
        assertEquals("Table name must be 'section'.", "section", table.TABLE_NAME);
        assertEquals("Column ID must be 'section_id'.", "section_id", table.COLUMN_ID);
        assertEquals("Table alias must be 's'.", "s", table.TABLE_ALIAS);
        assertEquals("Name column constant must be 'name'.", "name", table.NAME);
    }
    
    // --- Tests for simple column names (Mapper Alias: s.column_name) ---

    @Test
    public void testGetNAME() {
        System.out.println("getNAME - Mapper Alias (s.name)");
        assertEquals("s.name", table.getNAME());
    }

    @Test
    public void testGetDESCRIPTION() {
        System.out.println("getDESCRIPTION - Mapper Alias (s.description)");
        assertEquals("s.description", table.getDESCRIPTION());
    }

    @Test
    public void testGetFK_PERSON() {
        System.out.println("getFK_PERSON - Mapper Alias (s.person_id)");
        assertEquals("s.person_id", table.getFK_PERSON());
    }

    // -------------------------------------------------------------------

    // --- Tests for column names with SQL Alias (The corrected failing tests) ---
    
    @Test
    public void testGetNAMEWithAlias() {
        // Correction: Expected string must include escaped double quotes: AS "s.name"
        String expected = "s.name AS \"s.name\"";
        System.out.println("getNAMEWithAlias - SQL Select Clause (" + expected + ")");
        assertEquals(expected, table.getNAMEWithAlias());
    }

    @Test
    public void testGetDESCRIPTIONWithAlias() {
        // Correction: Expected string must include escaped double quotes: AS "s.description"
        String expected = "s.description AS \"s.description\"";
        System.out.println("getDESCRIPTIONWithAlias - SQL Select Clause (" + expected + ")");
        assertEquals(expected, table.getDESCRIPTIONWithAlias());
    }

    @Test
    public void testGetFK_PERSONWithAlias() {
        // Correction: Expected string must include escaped double quotes: AS "s.person_id"
        String expected = "s.person_id AS \"s.person_id\"";
        System.out.println("getFK_PERSONWithAlias - SQL Select Clause (" + expected + ")");
        assertEquals(expected, table.getFK_PERSONWithAlias());
    }
}