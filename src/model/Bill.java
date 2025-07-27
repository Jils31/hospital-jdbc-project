package model;

public class Bill {
    private int patientId;
    private int appointmentId;
    private double totalAmount;
    private String status;
    private String billingDate;

    public Bill(int patientId, int appointmentId, double totalAmount, String status, String billingDate) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.billingDate = billingDate;
    }

    public int getPatientId() { return patientId; }
    public int getAppointmentId() { return appointmentId; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getBillingDate() { return billingDate; }
}
