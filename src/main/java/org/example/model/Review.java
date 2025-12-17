package org.example.model;

import java.sql.Date;

public class Review {
    private int reviewID;
    private int order_ID;
    private int product_ID;
    private int customer_ID;
    private String revFeedback;
    private int revRating;      // 1 ile 5 arası
    private Date revDate;

    public Review() {}

    public Review(int reviewID, int order_ID, int product_ID, int customer_ID, String revFeedback, int revRating, Date revDate) {
        this.reviewID = reviewID;
        this.order_ID = order_ID;
        this.product_ID = product_ID;
        this.customer_ID = customer_ID;
        this.revFeedback = revFeedback;
        this.revRating = revRating;
        this.revDate = revDate;
    }

    // --- Getter ve Setter ---
    public int getReviewID() { return reviewID; }
    public void setReviewID(int reviewID) { this.reviewID = reviewID; }

    public int getOrder_ID() { return order_ID; }
    public void setOrder_ID(int order_ID) { this.order_ID = order_ID; }

    public int getProduct_ID() { return product_ID; }
    public void setProduct_ID(int product_ID) { this.product_ID = product_ID; }

    public int getCustomer_ID() { return customer_ID; }
    public void setCustomer_ID(int customer_ID) { this.customer_ID = customer_ID; }

    public String getRevFeedback() { return revFeedback; }
    public void setRevFeedback(String revFeedback) { this.revFeedback = revFeedback; }

    public int getRevRating() { return revRating; }
    public void setRevRating(int revRating) { this.revRating = revRating; }

    public Date getRevDate() { return revDate; }
    public void setRevDate(Date revDate) { this.revDate = revDate; }
}