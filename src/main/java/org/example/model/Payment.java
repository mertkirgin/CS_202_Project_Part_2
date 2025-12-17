package org.example.model;

import java.sql.Timestamp;

public class Payment {
    private int paymentID;
    private int order_ID;
    private Timestamp payDate;
    private double payAmount;   // DDL: DECIMAL(10,2)
    private String payMethod;   // "credit_card", "transfer", "wallet"
    private String payStatus;   // "success", "failed", "pending"

    public Payment() {}

    public Payment(int paymentID, int order_ID, Timestamp payDate, double payAmount, String payMethod, String payStatus) {
        this.paymentID = paymentID;
        this.order_ID = order_ID;
        this.payDate = payDate;
        this.payAmount = payAmount;
        this.payMethod = payMethod;
        this.payStatus = payStatus;
    }

    // --- Getter & Setter ---
    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }

    public int getOrder_ID() { return order_ID; }
    public void setOrder_ID(int order_ID) { this.order_ID = order_ID; }

    public Timestamp getPayDate() { return payDate; }
    public void setPayDate(Timestamp payDate) { this.payDate = payDate; }

    public double getPayAmount() { return payAmount; }
    public void setPayAmount(double payAmount) { this.payAmount = payAmount; }

    public String getPayMethod() { return payMethod; }
    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

    public String getPayStatus() { return payStatus; }
    public void setPayStatus(String payStatus) { this.payStatus = payStatus; }
}