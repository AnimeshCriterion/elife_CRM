package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BoxAlacarteList {

    @SerializedName("boxAlacarete")
    @Expose
    private List<BoxAlacarete> boxAlacarete = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<BoxAlacarete> getBoxAlacarete() {
        return boxAlacarete;
    }

    public void setBoxAlacarete(List<BoxAlacarete> boxAlacarete) {
        this.boxAlacarete = boxAlacarete;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class BoxAlacarete {

        @SerializedName("box_Alacarte_ID")
        @Expose
        private Integer boxAlacarteID;
        @SerializedName("cable_Box_ID")
        @Expose
        private Integer cableBoxID;
        @SerializedName("alacarte_ID")
        @Expose
        private Integer alacarteID;
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
        @SerializedName("alacarte_Name")
        @Expose
        private String alacarteName;

        public Integer getBoxAlacarteID() {
            return boxAlacarteID;
        }

        public void setBoxAlacarteID(Integer boxAlacarteID) {
            this.boxAlacarteID = boxAlacarteID;
        }

        public Integer getCableBoxID() {
            return cableBoxID;
        }

        public void setCableBoxID(Integer cableBoxID) {
            this.cableBoxID = cableBoxID;
        }

        public Integer getAlacarteID() {
            return alacarteID;
        }

        public void setAlacarteID(Integer alacarteID) {
            this.alacarteID = alacarteID;
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

        public String getAlacarteName() {
            return alacarteName;
        }

        public void setAlacarteName(String alacarteName) {
            this.alacarteName = alacarteName;
        }

    }

}
