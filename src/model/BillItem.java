package model;

public class BillItem {
    private int billId;
    private String itemType;
    private String description;
    private double amount;

    public BillItem(int billId, String itemType, String description, double amount) {
        this.billId = billId;
        this.itemType = itemType;
        this.description = description;
        this.amount = amount;
    }

    public int getBillId() { return billId; }
    public String getItemType() { return itemType; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}
