package org.example.model;

public class Category {
    private int categoryID;
    private String categoryName;
    private int admin_ID;

    public Category() {}

    public Category(int categoryID, String categoryName, int admin_ID) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.admin_ID = admin_ID;
    }

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getAdmin_ID() { return admin_ID; }
    public void setAdmin_ID(int admin_ID) { this.admin_ID = admin_ID; }

    @Override
    public String toString() {
        return categoryName;
    }
}