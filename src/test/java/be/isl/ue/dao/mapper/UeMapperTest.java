package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.UeTable;
import be.isl.ue.entity.Section;
import be.isl.ue.entity.UE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jessi
 */
public class UeMapperTest {
    
    // Nous avons besoin d'un UeTable pour les noms de colonnes/alias
    private UeTable ueTable;
    // Mock du SectionMapper pour isoler UEMapper
    private SectionMapper mockSectionMapper;
    // Mock du ResultSet pour simuler les données DB
    private ResultSet mockResultSet;
    // L'instance UEMapper à tester
    private UEMapper instance;
    
    // Données de test
    private final int UE_ID = 10;
    private final String UE_CODE = "DEV_SGBD";
    private final String UE_NAME = "Projet de dévelopement SGBD";
    private final String UE_DESCRIPTION = "Description SGBD";
    private final int UE_PERIODS = 100;
    private final boolean UE_IS_DECISIVE = true;
    private final LocalDateTime INSERTED_AT = LocalDateTime.of(2025, 1, 1, 10, 0);
    private final Timestamp TS_INSERTED_AT = Timestamp.valueOf(INSERTED_AT);
    
    // Section simulée retournée par mockSectionMapper
    private final Section mockSection = new Section(1, "IG", "Informatique de gestion", null, null, null);
    
    public UeMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        // 1. Initialiser les instances
        ueTable = new UeTable();
        
        // 2. Créer les mocks
        mockSectionMapper = mock(SectionMapper.class);
        mockResultSet = mock(ResultSet.class);
        
        // 3. Configurer le UEMapper à tester
        instance = new UEMapper(ueTable, mockSectionMapper);
        
        // 4. Configurer le comportement des mocks
        // Définir le comportement du SectionMapper
        when(mockSectionMapper.map(mockResultSet)).thenReturn(mockSection);
        
        // Définir le comportement du ResultSet
        when(mockResultSet.getInt(ueTable.getAliasCOLUMN_ID())).thenReturn(UE_ID);
        when(mockResultSet.getString(ueTable.getAliasCODE())).thenReturn(UE_CODE);
        when(mockResultSet.getString(ueTable.getAliasNAME())).thenReturn(UE_NAME);
        when(mockResultSet.getString(ueTable.getAliasDESCRIPTION())).thenReturn(UE_DESCRIPTION);
        when(mockResultSet.getInt(ueTable.getAliasNUMBER_OF_PERIODS())).thenReturn(UE_PERIODS);
        when(mockResultSet.getBoolean(ueTable.getAliasIS_DECISIVE())).thenReturn(UE_IS_DECISIVE);
        when(mockResultSet.getTimestamp(ueTable.getAliasINSERTED_AT())).thenReturn(TS_INSERTED_AT);
        // updated_at est null dans ce test
        when(mockResultSet.getTimestamp(ueTable.getAliasUPDATED_AT())).thenReturn(null);
    }
    
    @After
    public void tearDown() {
        instance = null;
        mockSectionMapper = null;
        mockResultSet = null;
    }

    /**
     * Test of map method, of class UeMapper.
     * Certitude: 100%
     */
    @Test
    public void testMap_ValidResultSet_ReturnsUEObject() {
        System.out.println("map");
        
        // Exécution de la méthode à tester
        UE result = instance.map(mockResultSet);
        
        // Vérification des résultats
        assertNotNull("Result should not be null", result);
        assertEquals(UE_ID, result.getId().intValue());
        assertEquals(UE_CODE, result.getCode());
        assertEquals(UE_NAME, result.getName());
        assertEquals(UE_DESCRIPTION, result.getDescription());
        assertEquals(UE_PERIODS, result.getNumberOfPeriods().intValue());
        assertEquals(UE_IS_DECISIVE, result.getIsDecisive());
        assertEquals(mockSection, result.getSection());
        assertEquals(INSERTED_AT, result.getInsertedAt());
        assertNull(result.getUpdatedAt()); // updated_at should be null
    }

    /**
     * Test of map method, of class UeMapper, when SQLException is thrown.
     * Certitude: 90% (Dépend de la gestion des logs dans la classe AbstractMapper)
     */
    @Test
    public void testMap_SQLException_ReturnsNull() throws SQLException {
        System.out.println("map_SQLException");
        
        // Configurer le ResultSet pour qu'il lance une exception à la première lecture
        when(mockResultSet.getInt(ueTable.getAliasCOLUMN_ID())).thenThrow(new SQLException("Simulated DB Error"));
        
        // Exécution de la méthode à tester
        UE result = instance.map(mockResultSet);
        
        // Vérification que le résultat est null en cas d'erreur
        assertNull("Result should be null when SQLException is thrown", result);
    }
    
}