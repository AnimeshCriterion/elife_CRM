package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BoxBouquetList {

    @SerializedName("boxBouquet")
    @Expose
    private List<BoxBouquet> boxBouquet = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<BoxBouquet> getBoxBouquet() {
        return boxBouquet;
    }

    public void setBoxBouquet(List<BoxBouquet> boxBouquet) {
        this.boxBouquet = boxBouquet;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class BoxBouquet {

        @SerializedName("box_Bouquet_ID")
        @Expose
        private Integer boxBouquetID;
        @SerializedName("cable_Box_ID")
        @Expose
        private Integer cableBoxID;
        @SerializedName("bouquet_ID")
        @Expose
        private Integer bouquetID;
        @SerializedName("price")
        @Expose
        private Float price;
        @SerializedName("tax_per")
        @Expose
        private Float taxPer;
        @SerializedName("tax")
        @Expose
        private Float tax;
        @SerializedName("total_Price")
        @Expose
        private Float totalPrice;
        @SerializedName("bouquet_Name")
        @Expose
        private String bouquetName;

        public Integer getBoxBouquetID() {
            return boxBouquetID;
        }

        public void setBoxBouquetID(Integer boxBouquetID) {
            this.boxBouquetID = boxBouquetID;
        }

        public Integer getCableBoxID() {
            return cableBoxID;
        }

        public void setCableBoxID(Integer cableBoxID) {
            this.cableBoxID = cableBoxID;
        }

        public Integer getBouquetID() {
            return bouquetID;
        }

        public void setBouquetID(Integer bouquetID) {
            this.bouquetID = bouquetID;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getTaxPer() {
            return taxPer;
        }

        public void setTaxPer(Float taxPer) {
            this.taxPer = taxPer;
        }

        public Float getTax() {
            return tax;
        }

        public void setTax(Float tax) {
            this.tax = tax;
        }

        public Float getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Float totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getBouquetName() {
            return bouquetName;
        }

        public void setBouquetName(String bouquetName) {
            this.bouquetName = bouquetName;
        }

    }
}
