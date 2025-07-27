package dao;

import model.Bill;
import model.BillItem;

import java.sql.*;

public class BillingDAO {
    private Connection conn;

    public BillingDAO(Connection conn) {
        this.conn = conn;
    }

    public int createBill(Bill b) {
        String sql = "INSERT INTO billing (patient_id, appointment_id, total_amount, payment_status, billing_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, b.getPatientId());
            stmt.setInt(2, b.getAppointmentId());
            stmt.setDouble(3, b.getTotalAmount());
            stmt.setString(4, b.getStatus());
            stmt.setString(5, b.getBillingDate());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Bill created with ID: " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Error creating bill: " + e.getMessage());
        }
        return -1;
    }

    public void addBillItem(BillItem item) {
        String sql = "INSERT INTO bill_items (bill_id, item_type, description, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getBillId());
            stmt.setString(2, item.getItemType());
            stmt.setString(3, item.getDescription());
            stmt.setDouble(4, item.getAmount());
            stmt.executeUpdate();
            System.out.println("Bill item added.");
        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    public void viewAllBills() {
        String sql = """
            SELECT b.bill_id, p.first_name, p.last_name, b.total_amount, b.payment_status, b.billing_date
            FROM billing b
            JOIN patients p ON b.patient_id = p.patient_id
            """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Billing Summary ---");
            while (rs.next()) {
                System.out.println("Bill ID: " + rs.getInt("bill_id") +
                        " | Patient: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                        " | Amount: ₹" + rs.getDouble("total_amount") +
                        " | Status: " + rs.getString("payment_status") +
                        " | Date: " + rs.getString("billing_date"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving bills: " + e.getMessage());
        }
    }
}
