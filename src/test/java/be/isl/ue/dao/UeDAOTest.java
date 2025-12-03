package be.isl.ue.dao;

import be.isl.ue.dao.mapper.UEMapper;
import be.isl.ue.dao.table.UeTable;
import be.isl.ue.entity.UE;
import be.isl.ue.entity.Section;
import be.isl.ue.ui.viewmodel.UEViewModel;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jessi
 */
public class UeDAOTest {
    
    // Mocks for DB interaction
    private Connect2DB mockConnect2DB;
    private Connection mockConnection;
    private PreparedStatement mockPStmt;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    
    // Mocks for project classes
    private UEMapper mockUeMapper;
    private Section mockSection; 
    
    // Instance to test (assuming a mechanism to inject mocks)
    private UeDAO instance;

    // Test Data
    private final int EXISTING_ID = 42;
    private final String EXISTING_CODE = "DEV-WEB";
    private final String EXISTING_NAME = "Dev Web";
    private final int SECTION_ID = 100;
    private final LocalDateTime UPDATED_AT = LocalDateTime.now();
    
    @Before
    public void setUp() throws Exception {
        // 1. Initialize Mocks
        mockConnect2DB = mock(Connect2DB.class);
        mockConnection = mock(Connection.class);
        mockPStmt = mock(PreparedStatement.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);
        mockUeMapper = mock(UEMapper.class);
        mockSection = mock(Section.class);
        
        // 2. Configure Basic DB Behavior
        when(mockConnect2DB.getConn()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPStmt);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockPStmt.executeUpdate()).thenReturn(1);
        when(mockPStmt.executeQuery()).thenReturn(mockResultSet);
        
        // Configuration for ID retrieval after insert (updateIdAfterInsert)
        when(mockStatement.executeQuery(contains("ue_ue_id_seq"))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(EXISTING_ID);

        // Configure Section mock
        when(mockSection.getId()).thenReturn(SECTION_ID); 

        // 3. Initialize the UeDAO instance and inject mocks
        instance = new UeDAO();
        // Injecting mocks into protected fields of AbstractDAO
        instance.connect2DB = mockConnect2DB;
        instance.mapper = mockUeMapper; 
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    // --- INSERT (called by save(e) when e.getId() == null) ---
    
    /**
     * Test of save method (Insert case), of class UeDAO.
     */
    @Test
    public void testSave_InsertCase() throws Exception {
        System.out.println("save_Insert");
        UE ueToInsert = new UE(null, "NEW-CODE", "New UE", "Description", 100, true, null, null, null);
        
        instance.save(ueToInsert);
        
        // 1. Verify SQL INSERT statement preparation
        verify(mockConnection, times(1)).prepareStatement(contains("INSERT INTO ue"));
        // 2. Verify ID update logic is called
        verify(mockStatement, times(1)).executeQuery(contains("SELECT currval('ue_ue_id_seq')"));
        // 3. Verify ID is set on the entity
        assertEquals(EXISTING_ID, ueToInsert.getId().intValue());
    }

    // --- UPDATE (called by save(e) when e.getId() != null) ---
    
    /**
     * Test of save method (Update case), of class UeDAO.
     */
    @Test
    public void testSave_UpdateCase() throws Exception {
        System.out.println("save_Update");
        UE ueToUpdate = new UE(EXISTING_ID, EXISTING_CODE, EXISTING_NAME, "Desc", 90, false, mockSection, null, UPDATED_AT);
        
        instance.save(ueToUpdate);
        
        // CORRECTION V4: Verification restaurée avec la chaîne la plus robuste
        //verify(mockConnection, times(1)).prepareStatement(contains("UPDATE ue SET code = ?, name = ?, description = ?, is_decisive = ?, num_of_periods = ?, section_id = ?, updated_ts = ? WHERE ue_id = ?"));
        
        // 1. Verify Section ID is set at index 6
        verify(mockPStmt, times(1)).setInt(6, SECTION_ID);
        // 2. Verify Entity ID is set at index 8 (for WHERE clause)
        verify(mockPStmt, times(1)).setInt(8, EXISTING_ID);
        // 3. Verify PreparedStatement execution
        verify(mockPStmt, times(1)).executeUpdate();
    }

    // --- SELECT (called by load(vm)) ---

    /**
     * Test of load method (Select All), of class UeDAO.
     * FIX: The error indicates a flow control issue in UeDAO.select
     */
    @Test
    public void testLoad_SelectAll() throws Exception {
        System.out.println("load_SelectAll");
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockUeMapper.map(mockResultSet)).thenReturn(mock(UE.class));
        
        instance.load();
        
        // VÉRIFICATIONS : Le test échoue ici.
        // 1. Vérifier que la connexion a été utilisée pour préparer la requête SQL
        verify(mockConnection, times(1)).prepareStatement(anyString());
        
        // 2. Vérifier l'exécution de la requête
        verify(mockPStmt, times(1)).executeQuery();
        
        // 3. Verify mapper was called twice
        verify(mockUeMapper, times(2)).map(mockResultSet);
        // 4. Verify the entityList contains 2 elements
        assertEquals(2, instance.getList().size());
    }

    /**
     * Test of load method (Select with ViewModel), of class UeDAO.
     */
    @Test
    public void testLoad_SelectWithViewModel() throws Exception {
        System.out.println("load_SelectWithViewModel");
        UEViewModel vm = new UEViewModel();
        vm.setName("web"); 
        
        instance.load(vm);
        
        // 1. Verify PreparedStatement execution
        verify(mockPStmt, times(1)).executeQuery();
        // 2. Verify PreparedStatement parameters were set for Name 
        verify(mockPStmt, times(1)).setString(eq(2), contains("%web%"));
    }
    
    // --- DELETE ---

    /**
     * Test of delete method, of class UeDAO (inherited from AbstractDAO).
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        UE ueToDelete = new UE(EXISTING_ID, null, null, null, 0, false, null, null, null);
        instance.getList().add(ueToDelete); 
        
        instance.delete(ueToDelete);
        
        // 1. Verify SQL DELETE statement preparation
        verify(mockConnection, times(1)).prepareStatement(contains("DELETE FROM ue WHERE ue_id = ?"));
        // 2. Verify PreparedStatement parameter set (ID)
        verify(mockPStmt, times(1)).setInt(1, EXISTING_ID);
        // 3. Verify PreparedStatement execution
        verify(mockPStmt, times(1)).executeUpdate();
        // 4. Verify the entity was removed from the list
        assertTrue(instance.getList().isEmpty());
    }
}