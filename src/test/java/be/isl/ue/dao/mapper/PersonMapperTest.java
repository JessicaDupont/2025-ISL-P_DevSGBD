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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author jessi
 */
public class PersonMapperTest {
    private PersonMapper instance;
    private PersonTable table;
    private ResultSet mockResultSet;

    // --- Données de Test ---
    private final Integer TEST_ID = 42;
    private final String TEST_FIRSTNAME = "JESSICA";
    private final String TEST_LASTNAME = "DUPONT";
    private final LocalDate TEST_DATE_OF_BIRTH = LocalDate.of(1990, 3, 12);
    private final String TEST_EMAIL = "j.dupont@test.be";
    private final String TEST_MOBILE = "0475123456";
    private final String TEST_ADDRESS = "Rue de la source 5/1";
    private final String TEST_POSTAL_CODE = "4570";
    private final String TEST_CITY = "Marchin";
    private final String TEST_COUNTRY = "Belgium";
    private final Boolean TEST_IS_JURY_MEMBER = false;
    private final Boolean TEST_IS_TEACHER = false;
    private final LocalDateTime TEST_INSERTED_AT = LocalDateTime.of(2025, 11, 10, 10, 0, 0);
    private final LocalDateTime TEST_UPDATED_AT = LocalDateTime.of(2025, 11, 13, 14, 0, 0);
    
    public PersonMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
    // 1. Initialisation des objets nécessaires (Mapper, Table)
        table = new PersonTable();
        instance = new PersonMapper(table);

        // 2. Création du Mock (simulation) du ResultSet
        mockResultSet = mock(ResultSet.class);
        
        // 3. Configuration des retours du Mock pour chaque colonne SQL
        when(mockResultSet.getInt(table.getAlias_Column(table.COLUMN_ID))).thenReturn(TEST_ID);
        when(mockResultSet.getString(table.getAlias_Column(table.FIRSTNAME))).thenReturn(TEST_FIRSTNAME);
        when(mockResultSet.getString(table.getAlias_Column(table.LASTNAME))).thenReturn(TEST_LASTNAME);
        when(mockResultSet.getDate(table.getAlias_Column(table.DATE_OF_BIRTH))).thenReturn(Date.valueOf(TEST_DATE_OF_BIRTH));
        when(mockResultSet.getString(table.getAlias_Column(table.EMAIL))).thenReturn(TEST_EMAIL);
        when(mockResultSet.getString(table.getAlias_Column(table.MOBILE))).thenReturn(TEST_MOBILE);
        when(mockResultSet.getString(table.getAlias_Column(table.ADDRESS))).thenReturn(TEST_ADDRESS);
        when(mockResultSet.getString(table.getAlias_Column(table.POSTAL_CODE))).thenReturn(TEST_POSTAL_CODE);
        when(mockResultSet.getString(table.getAlias_Column(table.CITY))).thenReturn(TEST_CITY);
        when(mockResultSet.getString(table.getAlias_Column(table.COUNTRY))).thenReturn(TEST_COUNTRY);
        when(mockResultSet.getBoolean(table.getAlias_Column(table.IS_JURY_MEMBER))).thenReturn(TEST_IS_JURY_MEMBER);
        when(mockResultSet.getBoolean(table.getAlias_Column(table.IS_TEACHER))).thenReturn(TEST_IS_TEACHER);
        when(mockResultSet.getTimestamp(table.getAlias_Column(table.INSERTED_AT))).thenReturn(Timestamp.valueOf(TEST_INSERTED_AT));
        when(mockResultSet.getTimestamp(table.getAlias_Column(table.UPDATED_AT))).thenReturn(Timestamp.valueOf(TEST_UPDATED_AT));}
    
    @After
    public void tearDown() {
    }

    /**
     * Test of map method, of class PersonMapper.
     */
    @Test
    public void testMap(){
        System.out.println("TEST PersonMapperTest");
        Person result = instance.map(mockResultSet);
        
        // Assertions : Vérification des résultats
        assertNotNull("The mapped Person object should not be null.", result);
        
        // 1. Vérification des champs
        assertEquals("ID must match.", TEST_ID, result.getId());
        assertEquals("First name must match.", TEST_FIRSTNAME, result.getFirstName());
        assertEquals("Last name must match.", TEST_LASTNAME, result.getLastName());
        assertEquals("Date of Birth must match.", TEST_DATE_OF_BIRTH, result.getDateOfBirth());
        assertEquals("Email must match.", TEST_EMAIL, result.getEmail());
        assertEquals("Mobile must match.", TEST_MOBILE, result.getMobile());
        assertEquals("Address must match.", TEST_ADDRESS, result.getAddress());
        assertEquals("Postal Code must match.", TEST_POSTAL_CODE, result.getPostalCode());
        assertEquals("City must match.", TEST_CITY, result.getCity());
        assertEquals("Country must match.", TEST_COUNTRY, result.getCountry());
        assertEquals("Is Jury Member must match.", TEST_IS_JURY_MEMBER, result.getIsJuryMember());
        assertEquals("Is Teacher must match.", TEST_IS_TEACHER, result.getIsTeacher());
        assertEquals("InsertedAt must match.", TEST_INSERTED_AT, result.getInsertedAt());
        assertEquals("UpdatedAt must match.", TEST_UPDATED_AT, result.getUpdatedAt());
    }
    
}
