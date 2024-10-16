package com.logicalsolutions.gruhaved.model;

public class MyProduct {

    private Integer productID;
    private String productName;
    private Integer priceUnit;
    private String productUnit;
    // Spinner value
    //  private String productImg;

    // Constructor
    public MyProduct(Integer productID, String productName, Integer priceUnit, String productUnit ) {
        this.productID = productID;
        this.productName = productName;
        this.priceUnit = priceUnit;
        this.productUnit = productUnit;
       // this.productImg = productImg;
    }

    // Getter and Setter for productID
    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for priceUnit
    public Integer getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Integer priceUnit) {
        this.priceUnit = priceUnit;
    }

    // Getter and Setter for productUnit (spinner value)
    public CharSequence getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
   /*  public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }*/
}
