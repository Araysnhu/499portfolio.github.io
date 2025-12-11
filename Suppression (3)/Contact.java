
public class Contact {
    private final String contactID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactID, String firstName, String lastName, String phone, String address) {
//Check for and reject null inputs, ensure string under 10 characters
        if (contactID == null || contactID.length() > 10) {
            throw new IllegalArgumentException("Your contact ID is not updatable and must be no longer than 10 characters non-null");
        }

        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Your first name input can not be null and no longer than 10 characters.");
        }

        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Your last name input can not be null and no longer than 10 characters.");
        }

        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Your phone number input can not be null and no longer than 10 characters.");
        }

        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Your address input can not be null and no longer than 10 characters.");
        }

        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

//Return outputs, insert error message for null values
    public String getContactID() {
        return contactID;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Your first name input can not be null and no longer than 10 characters.");
        }
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Your last name input can not be null and no longer than 10 characters.");
        }
        this.lastName = lastName;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Your phone number input can not be null and no longer than 10 characters.");
        }
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Your address input can not be null and no longer than 10 characters.");
        }
        this.address = address;
    }


    @Override
    public String toString() {
        return "Contact [contactID=" + contactID + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
                + phone + ", address=" + address + "]";
    }
}