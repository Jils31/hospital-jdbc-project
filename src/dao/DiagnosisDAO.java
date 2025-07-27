package dao;

import model.Diagnosis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiagnosisDAO {
    private Connection conn;

    public DiagnosisDAO(Connection conn) {
        this.conn = conn;
    }

    public void addDiagnosis(Diagnosis d) {
        String sql = "INSERT INTO diagnoses (appointment_id, diagnosis_text, diagnosis_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, d.getAppointmentId());
            pstmt.setString(2, d.getDiagnosisText());
            pstmt.setString(3, d.getDiagnosisDate());
            pstmt.executeUpdate();
            System.out.println("✅ Diagnosis recorded.");
        } catch (SQLException e) {
            System.out.println("❌ Error recording diagnosis: " + e.getMessage());
        }
    }

    public void viewAllDiagnoses() {
        String sql = "SELECT d.diagnosis_id, a.appointment_id, d.diagnosis_text, d.diagnosis_date, p.first_name, p.last_name " +
                     "FROM diagnoses d " +
                     "JOIN appointments a ON d.appointment_id = a.appointment_id " +
                     "JOIN patients p ON a.patient_id = p.patient_id";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Diagnoses ---");
            while (rs.next()) {
                System.out.println("Diagnosis ID: " + rs.getInt("diagnosis_id") +
                        ", Patient: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                        ", Date: " + rs.getString("diagnosis_date") +
                        ", Diagnosis: " + rs.getString("diagnosis_text"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving diagnoses: " + e.getMessage());
        }
    }
}
