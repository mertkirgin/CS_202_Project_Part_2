package org.example.model;

public class Product {
    private int productID;
    private int category_ID;
    private int catalog_ID;
    private String productName;
    private int productPrice;
    private String productDescription;
    private int productStockQuantity;

    public Product() {
    }

    public Product(int productID, int category_ID, int catalog_ID, String productName,
                   int productPrice, String productDescription, int productStockQuantity) {
        this.productID = productID;
        this.category_ID = category_ID;
        this.catalog_ID = catalog_ID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStockQuantity = productStockQuantity;
    }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public int getCategory_ID() { return category_ID; }
    public void setCategory_ID(int category_ID) { this.category_ID = category_ID; }

    public int getCatalog_ID() { return catalog_ID; }
    public void setCatalog_ID(int catalog_ID) { this.catalog_ID = catalog_ID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public int getProductStockQuantity() { return productStockQuantity; }
    public void setProductStockQuantity(int productStockQuantity) { this.productStockQuantity = productStockQuantity; }

    @Override
    public String toString() {
        return productName + " (" + productPrice + " TL) - Stok: " + productStockQuantity;
    }
}