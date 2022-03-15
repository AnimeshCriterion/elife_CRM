
package com.elifeindia.crm.model.generateinvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BoxBouquet {

    @SerializedName("bouquet_ID")
    @Expose
    private Integer bouquetID;
    @SerializedName("bouquet_Name")
    @Expose
    private String bouquetName;
    @SerializedName("box_Bouquet_ID")
    @Expose
    private Integer boxBouquetID;
    @SerializedName("cable_Box_ID")
    @Expose
    private Integer cableBoxID;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("tax")
    @Expose
    private Double tax;
    @SerializedName("tax_per")
    @Expose
    private String taxPer;
    @SerializedName("total_Price")
    @Expose
    private Double totalPrice;

    public BoxBouquet(Integer bouquetID, String bouquetName, Integer boxBouquetID, Integer cableBoxID, Double price, Double tax, String taxPer, Double totalPrice) {
        this.bouquetID = bouquetID;
        this.bouquetName = bouquetName;
        this.boxBouquetID = boxBouquetID;
        this.cableBoxID = cableBoxID;
        this.price = price;
        this.tax = tax;
        this.taxPer = taxPer;
        this.totalPrice = totalPrice;
    }
}
