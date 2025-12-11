import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class AppointmentTest {

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment("Standard", new Date(), "Customer scheduled for standard appointment.");
        assertNotNull(appointment);
    }

    @Test
    public void testAppointmentID() {
        Appointment appointment = new Appointment("Standard", new Date(), "Customer scheduled for standard appointment.");
        assertEquals("Standard", appointment.getAppointmentID());
    }

    @Test
    public void testAppointmentDate() {
        Date appointmentDate = new Date();
        Appointment appointment = new Appointment("Standard", appointmentDate, "Customer scheduled for standard appointment.");
        assertEquals(appointmentDate, appointment.getAppointmentDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppointmentDateInThePast() {
        Date appointmentDate = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        Appointment appointment = new Appointment("Standard", appointmentDate, "Customer scheduled for standard appointment.");
    }

    @Test
    public void testAppointmentDescription() {
        Appointment appointment = new Appointment("Standard", new Date(), "Customer scheduled for standard appointment.");
        assertEquals("Customer scheduled for standard appointment.", appointment.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppointmentIDTooLong() {
        Appointment appointment = new Appointment("Special", new Date(), "Customer scheduled for special appointment.");
    }

    @Test(expected = NullPointerException.class)
    public void testAppointmentIDNull() {
        Appointment appointment = new Appointment(null, new Date(), "Customer scheduled for special appointment.");
    }

    @Test(expected = NullPointerException.class)
    public void testAppointmentDateNull() {
        Appointment appointment = new Appointment("Standard", null, "Customer scheduled for standard appointment.");
    }

    @Test(expected = NullPointerException.class)
    public void testAppointmentDescriptionNull() {
        Appointment appointment = new Appointment("Standard", new Date(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppointmentDescriptionTooLong() {
        String description = "Error.Chosen description exceeds character 50 limit!";
        Appointment appointment = new Appointment("Standard", new Date(), description);
    }
}