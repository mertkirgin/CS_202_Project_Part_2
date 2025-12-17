package org.example;

import org.example.database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("⏳ Veritabanına bağlanılıyor (Environment Variables kullanılarak)...");

        // Try-with-resources bloğu: Bağlantıyı işimiz bitince otomatik kapatır.
        try (Connection connection = DBConnection.getConnection()) {

            if (connection != null) {
                System.out.println("✅ BAŞARILI! Bağlantı sağlandı.");
                System.out.println("--------------------------------------------------");

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
            System.err.println("❌ HATA: Bağlantı başarısız oldu!");
            System.err.println("Muhtemel Sebepler:");
            System.err.println("1. Run Configuration içindeki ŞİFRE yanlış olabilir (1234 mü 123456 mı?).");
            System.err.println("2. MySQL servisi çalışmıyor olabilir.");
            System.err.println("3. Veritabanı adı (URL içinde) yanlış olabilir.");

            System.out.println("\n--- Hata Detayı ---");
            e.printStackTrace();
        }
    }
}