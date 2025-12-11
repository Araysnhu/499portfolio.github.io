import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for Contact creation
public class ContactTest {

    // Test method for Contact creation
    @Test
    public void testContactCreation() {
        Contact contact = new Contact("1234567890", "Bruce", "Wayne","1234567890", "999 Bat St");

        // Assert that contact is not null
        assertNotNull(contact);

        // Assert that contact fields match the provided values
        assertEquals("1234567890", contact.getContactID());
        assertEquals("Bruce", contact.getFirstName());
        assertEquals("Wayne", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("999 Bat St", contact.getAddress());
    }
}