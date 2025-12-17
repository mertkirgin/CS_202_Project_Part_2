package org.example.database;

import org.example.model.Category;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // get all products (for customer view)
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productID"),
                        rs.getInt("category_ID"),
                        rs.getInt("catalog_ID"),
                        rs.getString("productName"),
                        rs.getInt("productPrice"), // Defined as INT in your DDL
                        rs.getString("productDescription"),
                        rs.getInt("productStockQuantity")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    // get products by spesific seller (for seller dash)
    public List<Product> getProductsBySeller(int sellerID) {
        List<Product> sellerProducts = new ArrayList<>();
        String sql = "SELECT P.* FROM Products P " +
                "JOIN Catalogs C ON P.catalog_ID = C.catalogID " +
                "WHERE C.seller_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sellerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productID"),
                        rs.getInt("category_ID"),
                        rs.getInt("catalog_ID"),
                        rs.getString("productName"),
                        rs.getInt("productPrice"),
                        rs.getString("productDescription"),
                        rs.getInt("productStockQuantity")
                );
                sellerProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellerProducts;
    }

    // seller to add a new product
    public boolean addProduct(Product product, int sellerID) {

        String sql = "INSERT INTO Products (category_ID, catalog_ID, productName, productPrice, productDescription, productStockQuantity) " +
                "VALUES (?, (SELECT catalogID FROM Catalogs WHERE seller_ID = ?), ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getCategory_ID());
            stmt.setInt(2, sellerID);
            stmt.setString(3, product.getProductName());
            stmt.setInt(4, product.getProductPrice());
            stmt.setString(5, product.getProductDescription());
            stmt.setInt(6, product.getProductStockQuantity());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(int productID) {
        String sql = "DELETE FROM Products WHERE productID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // query all categories
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                categories.add(new Category(
                        rs.getInt("categoryID"),
                        rs.getString("categoryName"),
                        rs.getInt("admin_ID")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return categories;
    }

    // filter products by cat.
    public List<Product> getProductsByCategory(int categoryID) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE category_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("productID"),
                        rs.getInt("category_ID"),
                        rs.getInt("catalog_ID"),
                        rs.getString("productName"),
                        rs.getInt("productPrice"),
                        rs.getString("productDescription"),
                        rs.getInt("productStockQuantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}