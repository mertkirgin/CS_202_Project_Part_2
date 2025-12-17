package org.example;

import org.example.database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Connecting to DB.");

        // Try-with-resources bloğu: Bağlantıyı işimiz bitince otomatik kapatır.
        try (Connection connection = DBConnection.getConnection()) {

            if (connection != null) {
                System.out.println("Connection Established.");

                // Basit bir test sorgusu atalım
                String sql = "SELECT uName, uRole FROM Users LIMIT 5";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                System.out.println("--- Kullanıcı Listesi (İlk 5) ---");
                while (rs.next()) {
                    String isim = rs.getString("uName");
                    String rol = rs.getString("uRole");
                    System.out.println("👤 " + isim + " (" + rol + ")");
                }

                System.out.println("--------------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error in DB connection.");
            e.printStackTrace();
        }
    }
}