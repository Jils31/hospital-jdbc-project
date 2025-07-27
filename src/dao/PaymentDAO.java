package dao;

import model.Payment;

import java.sql.*;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public void recordPayment(Payment p) {
        String sql = "INSERT INTO payments (bill_id, payment_date, amount, payment_method, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getBillId());
            stmt.setString(2, p.getPaymentDate());
            stmt.setDouble(3, p.getAmount());
            stmt.setString(4, p.getPaymentMethod());
            stmt.setString(5, p.getStatus());
            stmt.executeUpdate();
            System.out.println("✅ Payment recorded successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Error recording payment: " + e.getMessage());
        }
    }

    public void viewAllPayments() {
        String sql = """
            SELECT p.payment_id, b.bill_id, p.amount, p.payment_method, p.status, p.payment_date
            FROM payments p
            JOIN billing b ON p.bill_id = b.bill_id
            """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Payment Records ---");
            while (rs.next()) {
                System.out.println("Payment ID: " + rs.getInt("payment_id") +
                        " | Bill ID: " + rs.getInt("bill_id") +
                        " | Amount: ₹" + rs.getDouble("amount") +
                        " | Method: " + rs.getString("payment_method") +
                        " | Status: " + rs.getString("status") +
                        " | Date: " + rs.getString("payment_date"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching payments: " + e.getMessage());
        }
    }
}
