package com.elifeindia.crm.model;

import java.util.List;

public class BouquetModel {

    /**
     * bouquet : [{"bouquet_ID":1,"bouquet_Name":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true},{"bouquet_ID":1,"bouquet_Name":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true}]
     * status : sample string 1
     */

    private String status, total_Bouquet;
    private List<BouquetDTO> bouquet;

    public String getTotal_Bouquet() {
        return total_Bouquet;
    }

    public void setTotal_Bouquet(String total_Bouquet) {
        this.total_Bouquet = total_Bouquet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BouquetDTO> getBouquet() {
        return bouquet;
    }

    public void setBouquet(List<BouquetDTO> bouquet) {
        this.bouquet = bouquet;
    }

    public static class BouquetDTO {
        /**
         * bouquet_ID : 1
         * bouquet_Name : sample string 1
         * price : 1.0
         * tax : sample string 2
         * value : 1.0
         * tax_Amount : 1.0
         * is_select : true
         */

        private int bouquet_ID;
        private String bouquet_Name;
        private double price;
        private String tax;
        private double value;
        private double tax_Amount;
        private boolean is_select;

        public int getBouquet_ID() {
            return bouquet_ID;
        }

        public void setBouquet_ID(int bouquet_ID) {
            this.bouquet_ID = bouquet_ID;
        }

        public String getBouquet_Name() {
            return bouquet_Name;
        }

        public void setBouquet_Name(String bouquet_Name) {
            this.bouquet_Name = bouquet_Name;
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
