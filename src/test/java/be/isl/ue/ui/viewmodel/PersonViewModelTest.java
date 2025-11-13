package be.isl.ue.ui.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonViewModelTest {

    private PersonViewModel instance;

    @Before
    public void setUp() {
        instance = new PersonViewModel();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGettersAndSetters() {
        System.out.println("Testing PersonViewModel Getters and Setters");
        
        String firstName = "Ada";
        String lastName = "Lovelace";
        String city = "London";
        String dateOfBirth = "10/12/1815";
        String email = "ada.lovelace@math.com";

        // Set values
        instance.setFirstName(firstName);
        instance.setLastName(lastName);
        instance.setCity(city);
        instance.setDateOfBirth(dateOfBirth);
        instance.setEmail(email);

        // Assert Getters
        assertEquals("First name must match.", firstName, instance.getFirstName());
        assertEquals("Last name must match.", lastName, instance.getLastName());
        assertEquals("City must match.", city, instance.getCity());
        assertEquals("Date of birth must match.", dateOfBirth, instance.getDateOfBirth());
        assertEquals("Email must match.", email, instance.getEmail());
    }
}