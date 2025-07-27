package dao;

import model.Prescription;
import model.PrescriptionItem;

import java.sql.*;

public class PrescriptionDAO {
    private Connection conn;

    public PrescriptionDAO(Connection conn) {
        this.conn = conn;
    }

    public int createPrescription(Prescription p) {
        String sql = "INSERT INTO prescriptions (appointment_id, prescribed_date, notes) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, p.getAppointmentId());
            stmt.setString(2, p.getPrescribedDate());
            stmt.setString(3, p.getNotes());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("✅ Prescription Created with ID: " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error creating prescription: " + e.getMessage());
        }
        return -1;
    }

    public void addPrescriptionItem(PrescriptionItem item) {
        String sql = "INSERT INTO prescription_items (prescription_id, medication_id, dosage, duration, instructions) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getPrescriptionId());
            stmt.setInt(2, item.getMedicationId());
            stmt.setString(3, item.getDosage());
            stmt.setInt(4, item.getDuration());
            stmt.setString(5, item.getInstructions());
            stmt.executeUpdate();
            System.out.println("✅ Medication added to prescription.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding item: " + e.getMessage());
        }
    }

    public void viewAllPrescriptions() {
        String sql = """
            SELECT p.prescription_id, a.patient_id, pa.first_name, pa.last_name, p.prescribed_date, p.notes,
                   m.name AS medication_name, pi.dosage, pi.duration, pi.instructions
            FROM prescriptions p
            JOIN appointments a ON p.appointment_id = a.appointment_id
            JOIN patients pa ON a.patient_id = pa.patient_id
            JOIN prescription_items pi ON p.prescription_id = pi.prescription_id
            JOIN medications m ON pi.medication_id = m.medication_id
            """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- Prescriptions ---");
            while (rs.next()) {
                System.out.println("Patient: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                        " | Date: " + rs.getString("prescribed_date") +
                        " | Med: " + rs.getString("medication_name") +
                        " | Dosage: " + rs.getString("dosage") +
                        " | Duration: " + rs.getInt("duration") + " days" +
                        " | Instructions: " + rs.getString("instructions"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching prescriptions: " + e.getMessage());
        }
    }
}
