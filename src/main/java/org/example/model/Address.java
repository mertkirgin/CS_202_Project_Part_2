package org.example.model;

public class Address {
    private int addressID;
    private int user_ID;
    private String country;
    private String city;
    private String addressType; // "shipping", "billing", "other"
    private String line;
    private String postalCode;

    public Address() {}

    public Address(int addressID, int user_ID, String country, String city, String addressType, String line, String postalCode) {
        this.addressID = addressID;
        this.user_ID = user_ID;
        this.country = country;
        this.city = city;
        this.addressType = addressType;
        this.line = line;
        this.postalCode = postalCode;
    }

    public int getAddressID() { return addressID; }
    public void setAddressID(int addressID) { this.addressID = addressID; }

    public int getUser_ID() { return user_ID; }
    public void setUser_ID(int user_ID) { this.user_ID = user_ID; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { this.addressType = addressType; }

    public String getLine() { return line; }
    public void setLine(String line) { this.line = line; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public String toString() {
        return city + "/" + country + " - " + line + " (" + addressType + ")";
    }
}