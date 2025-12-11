import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AppointmentServiceTest {

    private AppointmentService appointmentService;

    @Before
    public void setUp() {
        appointmentService = new AppointmentService();
    }

    @Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment("#001", new Date(), "The first appointment.");
        assertTrue(appointmentService.addAppointment(appointment));
    }

    @Test
    public void testAddDuplicateAppointment() {
        Appointment appointment1 = new Appointment("#001", new Date(), "The second appointment.");
        Appointment appointment2 = new Appointment("#001", new Date(), "The third appointment.");
        appointmentService.addAppointment(appointment1);
        assertFalse(appointmentService.addAppointment(appointment2));
    }

    @Test
    public void testDeleteAppointment() {
        Appointment appointment = new Appointment("#001", new Date(), "The first appointment.");
        appointmentService.addAppointment(appointment);
        assertTrue(appointmentService.deleteAppointment("#001"));
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        assertFalse(appointmentService.deleteAppointment("#002"));
    }

    @Test
    public void testGetAppointment() {
        Appointment appointment = new Appointment("#001", new Date(), "The first appointment.");
        appointmentService.addAppointment(appointment);
        assertEquals(appointment, appointmentService.getAppointment("#001"));
    }

    @Test
    public void testGetNonExistentAppointment() {
        assertNull(appointmentService.getAppointment("#002"));
    }

    @Test
    public void testGetAppointments() {
        Appointment appointment1 = new Appointment("#001", new Date(), "The second appointment.");
        Appointment appointment2 = new Appointment("#002", new Date(), "The third appointment.");
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);
        assertEquals(2, appointmentService.getAppointments().size());
    }

}