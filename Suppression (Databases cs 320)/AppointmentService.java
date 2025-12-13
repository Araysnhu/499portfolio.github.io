import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AppointmentService {
    // Enhancement: Switched from List to Map for O(1) constant time complexity lookups.
    // This acts as your in-memory database and prevents duplicate ID iteration.
    private Map<String, Appointment> appointments;

    public AppointmentService() {
        appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Error. Chosen appointment is invalid. Please ensure that appointment is not null.");
        }

        // Optimized duplicate check using HashMap
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("No duplicate IDs allowed. Please select another ID.");
        }

        appointments.put(appointment.getAppointmentId(), appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Error. Chosen appointment is invalid. Please ensure that appointment is not null.");
        }

        // Optimized deletion using HashMap
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Error. Ensure that your desired input is correct and try again.");
        }

        appointments.remove(appointmentId);
    }

    // Enhancement: Added this method because your AppointmentServiceTest calls it,
    // but it was missing from your original class.
    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    // Returns a list to maintain backward compatibility if needed, but derived from the Map values
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments.values());
    }
}