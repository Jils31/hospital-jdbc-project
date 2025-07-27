package dao;

import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientDAO {
    private Connection conn;

    public PatientDAO(Connection conn) {
        this.conn = conn;
    }

    // Register a new patient
    public void registerPatient(Patient patient) {
        String sql = "INSERT INTO patients (first_name, last_name, dob, gender, contact_number, email, address, blood_group) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getDob());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getContactNumber());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getBloodGroup());

            stmt.executeUpdate();
            System.out.println("Patient registered successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering patient: " + e.getMessage());
        }
    }

    // View all patients
    public void viewAllPatients() {
        String sql = "SELECT * FROM patients";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Patient List ---");
            while (rs.next()) {
                int id = rs.getInt("patient_id");
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                String contact = rs.getString("contact_number");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", DOB: " + dob +
                        ", Gender: " + gender + ", Contact: " + contact + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
    }
}
