package org.example.database;

import org.example.model.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public boolean addReview(Review review) {
        String sql = "INSERT INTO Reviews (order_ID, product_ID, customer_ID, revFeedback, revRating, revDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, review.getOrder_ID());
            stmt.setInt(2, review.getProduct_ID());
            stmt.setInt(3, review.getCustomer_ID());
            stmt.setString(4, review.getRevFeedback());
            stmt.setInt(5, review.getRevRating());
            stmt.setDate(6, new Date(System.currentTimeMillis()));

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            // trigger throws exception if product is not shipped yet but user reviews it
            System.err.println("Cannot add review: " + e.getMessage());
            return false;
        }
    }

    // get reviews of a product
    public List<Review> getReviewsByProduct(int productID) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews WHERE product_ID = ? ORDER BY revDate DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("reviewID"),
                        rs.getInt("order_ID"),
                        rs.getInt("product_ID"),
                        rs.getInt("customer_ID"),
                        rs.getString("revFeedback"),
                        rs.getInt("revRating"),
                        rs.getDate("revDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}