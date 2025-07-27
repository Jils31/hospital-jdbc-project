package model;

public class Payment {
    private int billId;
    private String paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;

    public Payment(int billId, String paymentDate, double amount, String paymentMethod, String status) {
        this.billId = billId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getBillId() { return billId; }
    public String getPaymentDate() { return paymentDate; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
}
