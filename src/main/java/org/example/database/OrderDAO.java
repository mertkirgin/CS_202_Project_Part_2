package org.example.database;

import org.example.model.Order;
import org.example.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean createOrder(Order order, List<OrderItem> items) {
        Connection conn = null;
        PreparedStatement stmtOrder = null;
        PreparedStatement stmtItem = null;
        ResultSet generatedKeys = null;

        String insertOrderSQL = "INSERT INTO Orders (customer_ID, seller_ID, shipping_Address_ID, billing_AddressID, orderStatus) VALUES (?, ?, ?, ?, ?)";
        String insertItemSQL = "INSERT INTO OrderItems (order_ID, item_ID, unitPrice, oItemQuantity) VALUES (?, ?, ?, ?)";

        try {
            conn = DBConnection.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            // add orders table
            stmtOrder = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            stmtOrder.setInt(1, order.getCustomer_ID());
            stmtOrder.setInt(2, order.getSeller_ID());
            stmtOrder.setInt(3, order.getShipping_Address_ID());
            stmtOrder.setInt(4, order.getBilling_AddressID());
            stmtOrder.setString(5, "pending"); // Varsayılan durum

            int affectedRows = stmtOrder.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Sipariş oluşturulamadı.");
            }

            // get orderID
            generatedKeys = stmtOrder.getGeneratedKeys();
            int newOrderID = 0;
            if (generatedKeys.next()) {
                newOrderID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Sipariş ID alınamadı.");
            }

            // add to orderItems
            stmtItem = conn.prepareStatement(insertItemSQL);
            for (OrderItem item : items) {
                stmtItem.setInt(1, newOrderID);
                stmtItem.setInt(2, item.getItem_ID());
                stmtItem.setDouble(3, item.getUnitPrice());
                stmtItem.setInt(4, item.getoItemQuantity());
                stmtItem.addBatch(); // Toplu işlem havuzuna ekle
            }
            stmtItem.executeBatch(); // Hepsini tek seferde bas

            conn.commit();
            return true;

        } catch (SQLException e) {
            // roll back
            if (conn != null) {
                try {
                    System.err.println("Transaction error! Rolling back...");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (stmtOrder != null) stmtOrder.close();
                if (stmtItem != null) stmtItem.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getOrdersByCustomer(int customerID) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE customer_ID = ? ORDER BY orderDate DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getInt("customer_ID"),
                        rs.getInt("seller_ID"),
                        rs.getInt("shipping_Address_ID"),
                        rs.getInt("billing_AddressID"),
                        rs.getTimestamp("orderDate"),
                        rs.getString("orderStatus")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}