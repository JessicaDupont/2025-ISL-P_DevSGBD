package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.SectionTable;
import be.isl.ue.entity.Person;
import be.isl.ue.entity.Section;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for SectionMapper, checking if ResultSet data is correctly mapped to a Section entity.
 * * @author jessi
 */
public class SectionMapperTest {

    private SectionMapper instance;
    private SectionTable table;
    private PersonMapper personMapper;
    private ResultSet mockResultSet;
    private Person mockCoordinator;
    
    // Données de test
    private static final Integer TEST_ID = 101;
    private static final String TEST_NAME = "Java Dev";
    private static final String TEST_DESCRIPTION = "Java SE/EE Development";
    private static final LocalDateTime INSERTED_AT = LocalDateTime.of(2025, 11, 20, 15, 0, 0);
    private static final LocalDateTime UPDATED_AT = LocalDateTime.of(2025, 11, 20, 16, 0, 0);

    // Alias des colonnes (doivent correspondre aux retours de SectionTable.get...)
    private static final String ALIAS_ID = "s_section_id";
    private static final String ALIAS_NAME = "s_name";
    private static final String ALIAS_DESCRIPTION = "s_description";
    private static final String ALIAS_INSERTED_AT = "s_inserted_ts";
    private static final String ALIAS_UPDATED_AT = "s_updated_ts";


    @Before
    public void setUp() throws SQLException {
        // 1. Mock de SectionTable et de ses méthodes d'alias
        table = mock(SectionTable.class);
        when(table.getAliasCOLUMN_ID()).thenReturn(ALIAS_ID);
        when(table.getAliasNAME()).thenReturn(ALIAS_NAME);
        when(table.getAliasDESCRIPTION()).thenReturn(ALIAS_DESCRIPTION);
        when(table.getAliasINSERTED_AT()).thenReturn(ALIAS_INSERTED_AT);
        when(table.getAliasUPDATED_AT()).thenReturn(ALIAS_UPDATED_AT);

        // 2. Création de l'objet Personne (Coordinator) simulé
        mockCoordinator = new Person(42, "Mock", "DUPONT", null, null, null, null, null, null, null, false, false, INSERTED_AT, UPDATED_AT);
        
        // 3. Mock du PersonMapper imbriqué: il doit retourner le mockCoordinator
        personMapper = mock(PersonMapper.class);
        when(personMapper.map(any(ResultSet.class))).thenReturn(mockCoordinator);

        // 4. Instanciation du SectionMapper (Classe à tester)
        instance = new SectionMapper(table, personMapper);
        
        // 5. Mock du ResultSet: il doit retourner les valeurs de Section
        mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt(ALIAS_ID)).thenReturn(TEST_ID);
        when(mockResultSet.getString(ALIAS_NAME)).thenReturn(TEST_NAME);
        when(mockResultSet.getString(ALIAS_DESCRIPTION)).thenReturn(TEST_DESCRIPTION);
        when(mockResultSet.getTimestamp(ALIAS_INSERTED_AT)).thenReturn(Timestamp.valueOf(INSERTED_AT));
        when(mockResultSet.getTimestamp(ALIAS_UPDATED_AT)).thenReturn(Timestamp.valueOf(UPDATED_AT));
    }
    
    // Les autres méthodes de setup/teardown par défaut sont conservées vides pour la structure JUnit
    // ...

    /**
     * Test of getPersonMapper method, of class SectionMapper.
     */
    @Test
    public void testGetPersonMapper() {
        System.out.println("testGetPersonMapper");
        // Vérifie que le mapper imbriqué est bien celui que nous avons injecté
        PersonMapper result = instance.getPersonMapper();
        
        assertEquals("The returned PersonMapper must be the one set during instantiation.", this.personMapper, result);
    }

    /**
     * Test of map method, of class SectionMapper.
     */
    @Test
    public void testMap_ValidResultSet_ReturnsCorrectSection() throws SQLException {
        System.out.println("Testing SectionMapper: map(ResultSet)");
        
        Section result = instance.map(mockResultSet);
        
        assertNotNull("The mapped Section object should not be null.", result);
        
        // Vérification des champs de Section
        assertEquals("ID must match.", TEST_ID, result.getId());
        assertEquals("Name must match.", TEST_NAME, result.getName());
        assertEquals("Description must match.", TEST_DESCRIPTION, result.getDescription());
        
        // Vérification que le PersonMapper a été appelé pour mapper le coordinateur
        verify(personMapper, times(1)).map(mockResultSet);
        
        // Vérification du coordinateur (Personne)
        assertNotNull("Coordinator object must not be null.", result.getCoordinator());
        assertEquals("Coordinator ID must match (from mock).", mockCoordinator.getId(), result.getCoordinator().getId());
    }

    /**
     * Test of map method, of class SectionMapper, when an SQLException occurs.
     */
    @Test
    public void testMap_SQLException_ReturnsNull() throws SQLException {
        System.out.println("Testing SectionMapper: map(ResultSet) with SQLException");

        // Arrange: Force une méthode de ResultSet à lancer une SQLException (sur le nom de la section)
        when(mockResultSet.getString(ALIAS_NAME)).thenThrow(new SQLException("Simulated DB Error"));

        // Act
        Section result = instance.map(mockResultSet);

        // Assert
        assertNull("Mapping must return null on SQLException.", result);

    }
}