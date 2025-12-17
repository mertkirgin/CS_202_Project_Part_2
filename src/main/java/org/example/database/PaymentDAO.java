package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.ResultSet;

public class PaymentDAO {

    public boolean processPayment(int orderID, double amount, String method) {
        Connection conn = null;
        PreparedStatement stmtInsert = null;
        PreparedStatement stmtUpdate = null;
        ResultSet generatedKeys = null;

        String insertSQL = "INSERT INTO Payments (order_ID, payDate, payAmount, payMethod, payStatus) VALUES (?, ?, ?, ?, ?)";
        String updateSQL = "UPDATE Payments SET payStatus = 'success' WHERE paymentID = ?";

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            stmtInsert = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmtInsert.setInt(1, orderID);
            stmtInsert.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmtInsert.setDouble(3, amount);
            stmtInsert.setString(4, method);
            stmtInsert.setString(5, "pending"); // <-- ÖNEMLİ: İlk durum 'pending'

            int rows = stmtInsert.executeUpdate();
            if (rows == 0) throw new SQLException("Ödeme eklenemedi.");

            generatedKeys = stmtInsert.getGeneratedKeys();
            int paymentID = 0;
            if (generatedKeys.next()) {
                paymentID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Payment ID alınamadı.");
            }

            // trigger might work?????
            stmtUpdate = conn.prepareStatement(updateSQL);
            stmtUpdate.setInt(1, paymentID);
            stmtUpdate.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (stmtInsert != null) stmtInsert.close();
                if (stmtUpdate != null) stmtUpdate.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}