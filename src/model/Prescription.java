package model;

public class Prescription {
    private int appointmentId;
    private String prescribedDate;
    private String notes;

    public Prescription(int appointmentId, String prescribedDate, String notes) {
        this.appointmentId = appointmentId;
        this.prescribedDate = prescribedDate;
        this.notes = notes;
    }

    public int getAppointmentId() { return appointmentId; }
    public String getPrescribedDate() { return prescribedDate; }
    public String getNotes() { return notes; }
}
