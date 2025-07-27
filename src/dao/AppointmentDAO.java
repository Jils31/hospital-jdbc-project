package dao;

import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AppointmentDAO {
    private Connection conn;

    public AppointmentDAO(Connection conn) {
        this.conn = conn;
    }

    // Book an appointment
    public void bookAppointment(Appointment appt) {
        String sql = "INSERT INTO appointments (patient_id, staff_id, appointment_datetime, status, notes) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appt.getPatientId());
            stmt.setInt(2, appt.getStaffId());
            stmt.setString(3, appt.getDatetime());
            stmt.setString(4, appt.getStatus());
            stmt.setString(5, appt.getNotes());

            stmt.executeUpdate();
            System.out.println("Appointment booked successfully!");
        } catch (SQLException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }

    // View all appointments
    public void viewAllAppointments() {
        String sql = "SELECT a.appointment_id, a.patient_id, p.first_name, p.last_name, " +
                     "a.staff_id, s.first_name AS doc_fname, s.last_name AS doc_lname, " +
                     "a.appointment_datetime, a.status, a.notes " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN staff s ON a.staff_id = s.staff_id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Appointment List ---");
            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                String patientName = rs.getString("first_name") + " " + rs.getString("last_name");
                String doctorName = rs.getString("doc_fname") + " " + rs.getString("doc_lname");
                String datetime = rs.getString("appointment_datetime");
                String status = rs.getString("status");
                String notes = rs.getString("notes");

                System.out.println("Appointment ID: " + id + ", Patient: " + patientName +
                        ", Doctor: " + doctorName + ", DateTime: " + datetime +
                        ", Status: " + status + ", Notes: " + notes);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
    }
}
