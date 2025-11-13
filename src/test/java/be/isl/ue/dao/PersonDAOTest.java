package be.isl.ue.dao;

import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.PersonViewModel;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for PersonDAO. Requires a live connection to the PostgreSQL database.
 */
public class PersonDAOTest {
    
    private static PersonDAO dao;
    private Person testPerson;
    
    // Test data - using your own info for unique identification (safe as it's not inserted directly)
    private static final String TEST_LAST_NAME = "DAO_TEST_DUPONT";
    private static final String TEST_FIRST_NAME = "Jessica_DAO";
    private static final LocalDate TEST_DOB = LocalDate.of(1990, 3, 12);

    @BeforeClass
    public static void setUpClass() {
        // Initializes Connect2DB (Singleton logic) and DAO
        dao = new PersonDAO();
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Cleanup: Close the single DB connection after all tests run
        Connect2DB.close();
    }
    
    @Before
    public void setUp() {
        // Create a unique person for each test, without ID (to be inserted)
        testPerson = new Person(
            TEST_FIRST_NAME,
            TEST_LAST_NAME,
            TEST_DOB,
            "test_dao_jessica@isl.be",
            "0412345678",
            "Test Address",
            "1000",
            "Test City",
            "Test Country",
            true,
            false
        );
    }
    
    @After
    public void tearDown() {
        // Ensure the test person is deleted after each test if an ID was assigned
        if (testPerson != null && testPerson.getId() != null) {
            dao.delete(testPerson);
        }
        // Also load the list to clear the in-memory list in the DAO
        dao.load();
    }

    /**
     * Test sequence: INSERT -> LOAD -> UPDATE -> LOAD -> DELETE
     */
    @Test
    public void testCRUDOperations_Success() {
        System.out.println("Starting PersonDAO CRUD Integration Test...");
        
        // --- 1. INSERT Test ---
        System.out.println("1. Testing INSERT...");
        // The Person entity should not have an ID yet
        assertNull("Person ID must be null before insertion.", testPerson.getId());
        
        dao.insert(testPerson);
        
        // After insertion, the ID should be updated via updateIdAfterInsert
        assertNotNull("Person ID must be set after successful insertion.", testPerson.getId());
        Integer insertedId = testPerson.getId();

        // --- 2. LOAD Test (by ID check) ---
        System.out.println("2. Testing LOAD by ID (implicit via insert check)...");
        // Create a VM to load all entities for verification (can be optimized with a dedicated select by ID)
        List<Person> loadedList = dao.load(new PersonViewModel());
        
        Person loadedPerson = loadedList.stream()
                .filter(p -> p.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNotNull("The inserted person must be found in the loaded list.", loadedPerson);
        assertEquals("First name must match after load.", TEST_FIRST_NAME, loadedPerson.getFirstName());
        
        
        // --- 3. UPDATE Test ---
        System.out.println("3. Testing UPDATE...");
        String newFirstName = "JESSICA_UPDATED";
        loadedPerson.setFirstName(newFirstName);
        
        dao.update(loadedPerson);

        // Reload the list (simulating a fresh load from DB)
        dao.load();
        Person updatedPerson = dao.getList().stream()
                .filter(p -> p.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNotNull("The updated person must still be found.", updatedPerson);
        assertEquals("First name must be updated in the DB.", newFirstName, updatedPerson.getFirstName());
        
        
        // --- 4. DELETE Test ---
        System.out.println("4. Testing DELETE...");
        dao.delete(updatedPerson);
        
        // Reload the list (simulating a fresh load from DB)
        dao.load();
        Person deletedPersonCheck = dao.getList().stream()
                .filter(p -> p.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNull("The person must not be found after deletion.", deletedPersonCheck);
        testPerson.setId(null); // Clear ID to prevent double-delete in tearDown
        System.out.println("PersonDAO CRUD test finished successfully.");
    }
    
    // Add other relevant tests like:
    // @Test
    // public void testLoad_WithFilter() { ... }
    // @Test
    // public void testInsert_NullValues() { ... }
}