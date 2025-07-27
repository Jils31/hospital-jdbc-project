package model;

public class PrescriptionItem {
    private int prescriptionId;
    private int medicationId;
    private String dosage;
    private int duration;
    private String instructions;

    public PrescriptionItem(int prescriptionId, int medicationId, String dosage, int duration, String instructions) {
        this.prescriptionId = prescriptionId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
    }

    public int getPrescriptionId() { return prescriptionId; }
    public int getMedicationId() { return medicationId; }
    public String getDosage() { return dosage; }
    public int getDuration() { return duration; }
    public String getInstructions() { return instructions; }
}
