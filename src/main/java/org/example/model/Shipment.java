package org.example.model;

import java.sql.Date; // DDL'de DATE tipi kullanıldığı için

public class Shipment {
    private int shipmentID;
    private int order_ID;
    private String carrier;         // "FedEx", "DHL" vb.
    private String trackingNumber;  // Takip No
    private Date shippedDate;
    private Date deliveredDate;
    private String shipmentStatus;  // "pending", "shipped", "delivered"
    private int admin_ID;           // Kargolayan Admin

    public Shipment() {}

    public Shipment(int shipmentID, int order_ID, String carrier, String trackingNumber,
                    Date shippedDate, Date deliveredDate, String shipmentStatus, int admin_ID) {
        this.shipmentID = shipmentID;
        this.order_ID = order_ID;
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.shippedDate = shippedDate;
        this.deliveredDate = deliveredDate;
        this.shipmentStatus = shipmentStatus;
        this.admin_ID = admin_ID;
    }

    // --- Getter ve Setter ---
    public int getShipmentID() { return shipmentID; }
    public void setShipmentID(int shipmentID) { this.shipmentID = shipmentID; }

    public int getOrder_ID() { return order_ID; }
    public void setOrder_ID(int order_ID) { this.order_ID = order_ID; }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public Date getShippedDate() { return shippedDate; }
    public void setShippedDate(Date shippedDate) { this.shippedDate = shippedDate; }

    public Date getDeliveredDate() { return deliveredDate; }
    public void setDeliveredDate(Date deliveredDate) { this.deliveredDate = deliveredDate; }

    public String getShipmentStatus() { return shipmentStatus; }
    public void setShipmentStatus(String shipmentStatus) { this.shipmentStatus = shipmentStatus; }

    public int getAdmin_ID() { return admin_ID; }
    public void setAdmin_ID(int admin_ID) { this.admin_ID = admin_ID; }
}