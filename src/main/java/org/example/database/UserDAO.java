package org.example.database;

import org.example.model.Address;
import org.example.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE uEmail = ? AND uPassword = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("userID"),
                        rs.getString("uName"),
                        rs.getString("uEmail"),
                        rs.getString("uPassword"),
                        rs.getString("uPhone"),
                        rs.getString("uRole")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public List<Address> getUserAddresses(int userID) {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM Addresses WHERE user_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                addresses.add(new Address(
                        rs.getInt("addressID"),
                        rs.getInt("user_ID"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("addressType"),
                        rs.getString("line"),
                        rs.getString("postalCode")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return addresses;
    }
}