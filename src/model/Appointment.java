package model;

public class Appointment {
    private int patientId;
    private int staffId;
    private String datetime;
    private String status;
    private String notes;

    public Appointment(int patientId, int staffId, String datetime, String status, String notes) {
        this.patientId = patientId;
        this.staffId = staffId;
        this.datetime = datetime;
        this.status = status;
        this.notes = notes;
    }

    public int getPatientId() { return patientId; }
    public int getStaffId() { return staffId; }
    public String getDatetime() { return datetime; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
}
