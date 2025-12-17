package org.example.database;

import org.example.model.Shipment;
import java.sql.*;

public class ShipmentDAO {

    // create a new shipment
    public boolean createShipment(Shipment shipment) {
        String sql = "INSERT INTO Shipments (order_ID, carrier, trackingNumber, shippedDate, shipmentStatus, admin_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shipment.getOrder_ID());
            stmt.setString(2, shipment.getCarrier());
            stmt.setString(3, shipment.getTrackingNumber());
            stmt.setDate(4, new Date(System.currentTimeMillis()));
            stmt.setString(5, "shipped");
            stmt.setInt(6, shipment.getAdmin_ID());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // make the cargo delivered (admin trigger)
    public boolean markAsDelivered(int shipmentID, int adminID) {
        String sql = "UPDATE Shipments SET shipmentStatus = 'delivered', deliveredDate = ?, admin_ID = ? WHERE shipmentID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setInt(2, adminID); // Güncelleyen admin
            stmt.setInt(3, shipmentID);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}