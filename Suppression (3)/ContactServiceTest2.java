import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for ContactService methods
public class ContactServiceTest {

    // Test method to add a contact
    @Test
    public void testAddContact() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);

        // Assert that the added contact is retrievable
        assertEquals(contact, contactService.getContact("1234567890"));
    }

    // Test method to delete a contact
    @Test
    public void testDeleteContact() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");

        // Assert that the deleted contact is no longer retrievable
        assertNull(contactService.getContact("1234567890"));
    }

    // Test method to update the first name of a contact
    @Test
    public void testUpdateFirstName() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);
        contactService.updateFirstName("1234567890", "Clark");

        // Assert that the first name is updated correctly
        assertEquals("Clark", contactService.getContact("1234567890").getFirstName());
    }

    // Test method to update the last name of a contact
    @Test
    public void testUpdateLastName() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);
        contactService.updateLastName("1234567890", "Kent");

        // Assert that the last name is updated correctly
        assertEquals("Smith", contactService.getContact("1234567890").getLastName());
    }

    // Test method to update the phone number of a contact
    @Test
    public void testUpdatePhone() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);
        contactService.updatePhone("1234567890", "0987654321");

        // Assert that the phone number is updated correctly
        assertEquals("0987654321", contactService.getContact("1234567890").getPhone());
    }

    // Test method to update the address of a contact
    @Test
    public void testUpdateAddress() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("1234567890", "Bruce", "Wayne", "1234567890", "999 Bat St");
        contactService.addContact(contact);
        contactService.updateAddress("1234567890", "234 Super ave");

        // Assert that the address is updated correctly
        assertEquals("234 Super ave", contactService.getContact("1234567890").getAddress());
    }
}