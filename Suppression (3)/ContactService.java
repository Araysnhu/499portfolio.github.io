import java.util.HashMap;
import java.util.Map;

public class ContactService {
    // Map contacts and use constructor
    private Map<String, Contact> contacts;

    public ContactService() {
        contacts = new HashMap<>();
    }

    // Add new contact
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID cannot be repeated.");
        }
        contacts.put(contact.getContactID(), contact);
    }

    // Delete contact
    public void deleteContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
        contacts.remove(contactID);
    }

    //Update first name
    public void updateFirstName(String contactID, String newFirstName) {
        Contact contact = contacts.get(contactID);
        if (contact != null) {
            contact.setFirstName(newFirstName);
        } else {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
    }

    //Update last name
    public void updateLastName(String contactID, String newLastName) {
        Contact contact = contacts.get(contactID);
        if (contact != null) {
            contact.setLastName(newLastName);
        } else {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
    }

    //Update phone number
    public void updatePhone(String contactID, String newPhone) {
        Contact contact = contacts.get(contactID);
        if (contact != null) {
            contact.setPhone(newPhone);
        } else {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
    }

    //Update address
    public void updateAddress(String contactID, String newAddress) {
        Contact contact = contacts.get(contactID);
        if (contact != null) {
            contact.setAddress(newAddress);
        } else {
            throw new IllegalArgumentException("Unable to locate contact ID.");
        }
    }

    // Return contact using ID
    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }
}
