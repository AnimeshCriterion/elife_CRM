package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductList {

    @SerializedName("product")
    @Expose
    public List<Product> product = null;
    @SerializedName("status")
    @Expose
    public String status;

    public List<Product> getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }

    public static class Product {

        @SerializedName("product_ID")
        @Expose
        public Integer productID;
        @SerializedName("product_Name")
        @Expose
        public String productName;

        public Integer getProductID() {
            return productID;
        }

        public String getProductName() {
            return productName;
        }
    }

}


