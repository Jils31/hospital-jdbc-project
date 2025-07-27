package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffDAO {
    private Connection conn;

    public StaffDAO(Connection conn) {
        this.conn = conn;
    }

    public void viewAllStaff() {
        String sql = "SELECT staff_id, first_name, last_name, role, specialization FROM staff";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Hospital Staff ---");
            while (rs.next()) {
                int id = rs.getInt("staff_id");
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String role = rs.getString("role");
                String specialization = rs.getString("specialization");

                System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role + 
                                   (specialization != null ? ", Specialization: " + specialization : ""));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving staff: " + e.getMessage());
        }
    }
}
