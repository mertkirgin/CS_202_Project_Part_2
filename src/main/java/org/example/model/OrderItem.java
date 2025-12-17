package org.example.model;

public class OrderItem {
    private int order_ID;
    private int item_ID;
    private double unitPrice;
    private int oItemQuantity;

    public OrderItem() {}

    public OrderItem(int order_ID, int item_ID, double unitPrice, int oItemQuantity) {
        this.order_ID = order_ID;
        this.item_ID = item_ID;
        this.unitPrice = unitPrice;
        this.oItemQuantity = oItemQuantity;
    }

    public int getOrder_ID() { return order_ID; }
    public void setOrder_ID(int order_ID) { this.order_ID = order_ID; }

    public int getItem_ID() { return item_ID; }
    public void setItem_ID(int item_ID) { this.item_ID = item_ID; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public int getoItemQuantity() { return oItemQuantity; }
    public void setoItemQuantity(int oItemQuantity) { this.oItemQuantity = oItemQuantity; }
}