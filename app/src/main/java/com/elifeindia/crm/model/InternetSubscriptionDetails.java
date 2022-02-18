package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InternetSubscriptionDetails {

    @SerializedName("boxpackage")
    @Expose
    private List<Boxpackage> boxpackage = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<Boxpackage> getBoxpackage() {
        return boxpackage;
    }

    public void setBoxpackage(List<Boxpackage> boxpackage) {
        this.boxpackage = boxpackage;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class Boxpackage {

        @SerializedName("box_Package_ID")
        @Expose
        private Integer boxPackageID;
        @SerializedName("internet_Box_ID")
        @Expose
        private Integer internetBoxID;
        @SerializedName("package_ID")
        @Expose
        private Integer packageID;
        @SerializedName("price")
        @Expose
        private Float price;
        @SerializedName("tax_Percent")
        @Expose
        private Float taxPercent;
        @SerializedName("tax_Amount")
        @Expose
        private Float taxAmount;
        @SerializedName("pricewithouttax")
        @Expose
        private Float pricewithouttax;
        @SerializedName("package")
        @Expose
        private String _package;

        public Integer getBoxPackageID() {
            return boxPackageID;
        }

        public void setBoxPackageID(Integer boxPackageID) {
            this.boxPackageID = boxPackageID;
        }

        public Integer getInternetBoxID() {
            return internetBoxID;
        }

        public void setInternetBoxID(Integer internetBoxID) {
            this.internetBoxID = internetBoxID;
        }

        public Integer getPackageID() {
            return packageID;
        }

        public void setPackageID(Integer packageID) {
            this.packageID = packageID;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getTaxPercent() {
            return taxPercent;
        }

        public void setTaxPercent(Float taxPercent) {
            this.taxPercent = taxPercent;
        }

        public Float getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(Float taxAmount) {
            this.taxAmount = taxAmount;
        }

        public Float getPricewithouttax() {
            return pricewithouttax;
        }

        public void setPricewithouttax(Float pricewithouttax) {
            this.pricewithouttax = pricewithouttax;
        }

        public String getPackage() {
            return _package;
        }

        public void setPackage(String _package) {
            this._package = _package;
        }

    }
}
