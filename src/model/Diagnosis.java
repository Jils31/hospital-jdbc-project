package model;

public class Diagnosis {
    private int appointmentId;
    private String diagnosisText;
    private String diagnosisDate;

    public Diagnosis(int appointmentId, String diagnosisText, String diagnosisDate) {
        this.appointmentId = appointmentId;
        this.diagnosisText = diagnosisText;
        this.diagnosisDate = diagnosisDate;
    }

    public int getAppointmentId() { return appointmentId; }
    public String getDiagnosisText() { return diagnosisText; }
    public String getDiagnosisDate() { return diagnosisDate; }
}
