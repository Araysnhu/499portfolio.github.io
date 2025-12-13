import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments;

    public AppointmentService() {
        appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Error.Chosen appointment is invalid. Please ensure that appointment is not null.");
        }

        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getAppointmentId().equals(appointment.getAppointmentId())) {
                throw new IllegalArgumentException("No duplicate IDs allowed. Please select another ID.");
            }
        }

        appointments.add(appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Error. Chosen appointment is invalid. Please ensure that appointment is not null.");
        }

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointments.remove(appointment);
                return;
            }
        }

        throw new IllegalArgumentException("Error. Ensure that your desired input is correct and try again." );
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}