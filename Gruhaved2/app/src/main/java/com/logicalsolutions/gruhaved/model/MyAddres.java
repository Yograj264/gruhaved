package com.logicalsolutions.gruhaved.model;

public class MyAddres {
    public Integer AddressID;
    public String FullName;
    public String Contact;
    public String FlatNo;
    public String Wing;
    public String  Society;
    public String City;
    public Integer Pincode;

    public MyAddres(Integer addressID, String fullName, String contact, String flatNo, String wing, String society, String city, Integer pincode) {
        AddressID = addressID;
        FullName = fullName;
        Contact = contact;
        FlatNo = flatNo;
        Wing = wing;
        Society = society;
        City = city;
        Pincode = pincode;
    }

    public Integer getAddressID() {
        return AddressID;
    }


    public void setAddressID(Integer addressID) {
        AddressID = addressID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getFlatNo() {
        return FlatNo;
    }

    public void setFlatNo(String flatNo) {
        FlatNo = flatNo;
    }

    public String getWing() {
        return Wing;
    }

    public void setWing(String wing) {
        Wing = wing;
    }

    public String getSociety() {
        return Society;
    }

    public void setSociety(String society) {
        Society = society;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Integer getPincode() {
        return Pincode;
    }

    public void setPincode(Integer pincode) {
        Pincode = pincode;
    }
}
