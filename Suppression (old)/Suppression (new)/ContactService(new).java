import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactService {
    // ENHANCEMENT 1: Use ConcurrentHashMap for thread safety
    // This supports high concurrency for web/app environments.
    private final Map<String, Contact> contacts;

    public ContactService() {
        // Initialize as ConcurrentHashMap
        contacts = new ConcurrentHashMap<>();
    }

    // Add new contact
    public void addContact(Contact contact) {
        if (contact == null || contact.getContactID() == null) {
            throw new IllegalArgumentException("Invalid contact data.");
        }
        
        // ENHANCEMENT 2: Atomic Check-and-Put
        // putIfAbsent returns null if the key was free and added successfully.
        // It returns the existing value if the key already existed.
        // This is thread-safe compared to .containsKey() followed by .put().
        if (contacts.putIfAbsent(contact.getContactID(), contact) != null) {
            throw new IllegalArgumentException("Contact ID cannot be repeated.");
        }
    }

    // Delete contact
    public void deleteContact(String contactID) {
        if (contactID == null || contacts.remove(contactID) == null) {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
    }

    // Update first name
    public void updateFirstName(String contactID, String newFirstName) {
        Contact contact = getContactOrThrow(contactID);
        contact.setFirstName(newFirstName);
    }

    // Update last name
    public void updateLastName(String contactID, String newLastName) {
        Contact contact = getContactOrThrow(contactID);
        contact.setLastName(newLastName);
    }

    // Update phone number
    public void updatePhone(String contactID, String newPhone) {
        Contact contact = getContactOrThrow(contactID);
        contact.setPhone(newPhone);
    }

    // Update address
    public void updateAddress(String contactID, String newAddress) {
        Contact contact = getContactOrThrow(contactID);
        contact.setAddress(newAddress);
    }

    // Return contact using ID
    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }

    // ENHANCEMENT 3: Helper method for DRY (Don't Repeat Yourself) code
    // Centralizes the "find or throw" logic used in all update methods.
    private Contact getContactOrThrow(String contactID) {
        Contact contact = contacts.get(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
        return contact;
    }
}