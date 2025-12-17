package org.example.model;

public class User {
    private int userID;
    private String uName;
    private String uEmail;
    private String uPassword;
    private String uPhone;
    private String uRole;

    public User() {
    }

    public User(int userID, String uName, String uEmail, String uPassword, String uPhone, String uRole) {
        this.userID = userID;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPassword = uPassword;
        this.uPhone = uPhone;
        this.uRole = uRole;
    }


    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getuName() { return uName; }
    public void setuName(String uName) { this.uName = uName; }

    public String getuEmail() { return uEmail; }
    public void setuEmail(String uEmail) { this.uEmail = uEmail; }

    public String getuPassword() { return uPassword; }
    public void setuPassword(String uPassword) { this.uPassword = uPassword; }

    public String getuPhone() { return uPhone; }
    public void setuPhone(String uPhone) { this.uPhone = uPhone; }

    public String getuRole() { return uRole; }
    public void setuRole(String uRole) { this.uRole = uRole; }

    @Override
    public String toString() {
        return uName + " (" + uRole + ")";
    }
}