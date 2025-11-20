package be.isl.ue.dao;

import be.isl.ue.entity.Person;
import be.isl.ue.entity.Section;
import be.isl.ue.ui.viewmodel.SectionViewModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for SectionDAO. Requires a live connection to the PostgreSQL database.
 */
public class SectionDAOTest {
    
    private static SectionDAO dao;
    private static PersonDAO personDao;
    private Section testSection;
    private Person testCoordinator;
    
    private static final String TEST_NAME = "DAO_TEST_SECTION";
    private static final String TEST_DESCRIPTION = "Integration Test Section";
    private static final String TEST_COORD_LASTNAME = "DAO_TEST_COORD";
    private static final String TEST_COORD_FIRSTNAME = "Coord_DAO";

    @BeforeClass
    public static void setUpClass() {
        // Initializes Connect2DB (Singleton logic) and DAOs
        dao = new SectionDAO();
        personDao = new PersonDAO();
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Cleanup: Close the single DB connection after all tests run
        Connect2DB.close();
    }
    
    @Before
    public void setUp() {
        // Create a unique coordinator for the section before each test
        testCoordinator = new Person(TEST_COORD_FIRSTNAME, TEST_COORD_LASTNAME, LocalDate.of(1990, 3, 12), 
                "test.coord@test.be", "123456789", "Test Address", "1000", "Test City", "Test Country", true, true);
        
        // This relies on PersonDAO insert being functional
        personDao.save(testCoordinator); 
        System.out.println("Coordinator : "+testCoordinator.toString());
        
        testSection = new Section(TEST_NAME, TEST_DESCRIPTION, testCoordinator);
    }
    
    @After
    public void tearDown() {
        // Global Cleanup: Ensure the test section and its coordinator are deleted
        if (testSection.getId() != null) {
            dao.delete(testSection); 
        }
        if (testCoordinator.getId() != null) {
            personDao.delete(testCoordinator);
        }
    }

    /**
     * Test of save (Insert), load (Read), update, and delete methods, of class SectionDAO.
     * This method performs a complete CRUD cycle.
     */
    @Test
    public void testCRUD() {
        System.out.println("--- Starting SectionDAO CRUD Test ---");
        
        // --- 1. INSERT Test (and implicit SAVE) ---
        System.out.println("1. Testing INSERT (SAVE)...");
        dao.save(testSection); // The ID should be set here
        
        Integer insertedId = testSection.getId();
        assertNotNull("The inserted Section must have an ID.", insertedId);
        System.out.println("ID : "+insertedId);
        // --- 2. LOAD Test (Read All/Read One) ---
        System.out.println("2. Testing LOAD (READ)...");
        dao.load();
        
        Section loadedSection = dao.getList().stream()
                .filter(s -> s.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNotNull("The inserted section must be found in the loaded list.", loadedSection);
        assertEquals("Name must match.", TEST_NAME, loadedSection.getName());
        assertEquals("Coordinator must be loaded.", testCoordinator.getId(), loadedSection.getCoordinator().getId());
        
        // --- 3. UPDATE Test ---
        System.out.println("3. Testing UPDATE...");
        String newName = "SECTION_UPDATED";
        loadedSection.setName(newName);
        
        dao.save(loadedSection); // Calls update internally

        // Reload the list (simulating a fresh load from DB)
        dao.load();
        Section updatedSection = dao.getList().stream()
                .filter(s -> s.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNotNull("The updated section must still be found.", updatedSection);
        assertEquals("Name must be updated in the DB.", newName, updatedSection.getName());
        
        
        // --- 4. DELETE Test ---
        System.out.println("4. Testing DELETE...");
        dao.delete(updatedSection);
        
        // Reload the list (simulating a fresh load from DB)
        dao.load();
        Section deletedSectionCheck = dao.getList().stream()
                .filter(s -> s.getId().equals(insertedId))
                .findFirst()
                .orElse(null);
        
        assertNull("The section must not be found after deletion.", deletedSectionCheck);
        testSection.setId(null); // Clear ID to prevent double-delete in tearDown
        System.out.println("SectionDAO CRUD test finished successfully.");
    }
    
    /**
     * Test of load method with a ViewModel filter.
     */
    @Test
    public void testLoad_WithFilter() {
        System.out.println("--- Starting SectionDAO Load With Filter Test ---");
        
        // Insert test data
        dao.save(testSection); 
        
        // 1. Prepare a ViewModel to filter by coordinator's last name
        SectionViewModel vm = new SectionViewModel();
        vm.setCoordinatorLastName(TEST_COORD_LASTNAME);
        
        // 2. Load with filter
        List<Section> filteredList = dao.load(vm);
        
        // 3. Assertions
        assertFalse("The filtered list should not be empty.", filteredList.isEmpty());
        assertTrue("The filtered list should contain the test section.", 
                filteredList.stream().anyMatch(s -> s.getId().equals(testSection.getId())));
        
        // 4. Test a filter that should return nothing
        vm.setCoordinatorLastName("NON_EXISTENT_COORDINATOR");
        List<Section> emptyList = dao.load(vm);
        assertTrue("The list should be empty with a non-matching filter.", emptyList.isEmpty());
        
        System.out.println("SectionDAO Load With Filter test finished successfully.");
    }
}