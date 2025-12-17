package org.example.model;

import java.sql.Timestamp;

public class Order {
    // Senin Orders tablosundaki birebir sütun isimleri
    private int orderID;
    private int customer_ID;
    private int seller_ID;           // DDL'de bu zorunlu (Her sipariş tek satıcıya ait)
    private int shipping_Address_ID;
    private int billing_AddressID;
    private Timestamp orderDate;
    private String orderStatus;      // 'pending', 'shipped' vb.

    public Order() {}

    public Order(int orderID, int customer_ID, int seller_ID, int shipping_Address_ID,
                 int billing_AddressID, Timestamp orderDate, String orderStatus) {
        this.orderID = orderID;
        this.customer_ID = customer_ID;
        this.seller_ID = seller_ID;
        this.shipping_Address_ID = shipping_Address_ID;
        this.billing_AddressID = billing_AddressID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    // --- Getter ve Setter ---
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public int getCustomer_ID() { return customer_ID; }
    public void setCustomer_ID(int customer_ID) { this.customer_ID = customer_ID; }

    public int getSeller_ID() { return seller_ID; }
    public void setSeller_ID(int seller_ID) { this.seller_ID = seller_ID; }

    public int getShipping_Address_ID() { return shipping_Address_ID; }
    public void setShipping_Address_ID(int shipping_Address_ID) { this.shipping_Address_ID = shipping_Address_ID; }

    public int getBilling_AddressID() { return billing_AddressID; }
    public void setBilling_AddressID(int billing_AddressID) { this.billing_AddressID = billing_AddressID; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    @Override
    public String toString() {
        return "Sipariş #" + orderID + " (" + orderStatus + ")";
    }
}