package com.elifeindia.crm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InternetPackageModel {

    /**
     * packages : [{"package_ID":1,"package":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true},{"package_ID":1,"package":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true}]
     * status : sample string 1
     * total_Package : 1.0
     */

    private String status;
    private double total_Package;
    private List<PackagesDTO> packages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_Package() {
        return total_Package;
    }

    public void setTotal_Package(double total_Package) {
        this.total_Package = total_Package;
    }

    public List<PackagesDTO> getPackages() {
        return packages;
    }

    public void setPackages(List<PackagesDTO> packages) {
        this.packages = packages;
    }

    public static class PackagesDTO {
        /**
         * package_ID : 1
         * package : sample string 1
         * price : 1.0
         * tax : sample string 2
         * value : 1.0
         * tax_Amount : 1.0
         * is_select : true
         */

        private int package_ID;
        @SerializedName("package")
        private String packageX;
        private double price;
        private String tax;
        private double value;
        private double tax_Amount;
        private boolean is_select;

        public int getPackage_ID() {
            return package_ID;
        }

        public void setPackage_ID(int package_ID) {
            this.package_ID = package_ID;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getTax_Amount() {
            return tax_Amount;
        }

        public void setTax_Amount(double tax_Amount) {
            this.tax_Amount = tax_Amount;
        }

        public boolean isIs_select() {
            return is_select;
        }

        public void setIs_select(boolean is_select) {
            this.is_select = is_select;
        }
    }
}
