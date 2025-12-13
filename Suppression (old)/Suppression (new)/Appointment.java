import java.util.Date;

public class Appointment {
    private String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Error.Chosen ID cannot be null or exceed 10 characters. Please try again.");
        }
        this.appointmentId = appointmentId;

        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Error.Chosen date cannot be null or have already passed. Please try again.");
        }
        this.appointmentDate = appointmentDate;

        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Error.Chosen description cannot be null or exceed 50 characters. Please try again.");
        }
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getDescription() {
        return description;
    }
}