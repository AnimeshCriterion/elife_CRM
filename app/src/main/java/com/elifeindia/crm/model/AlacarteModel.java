package com.elifeindia.crm.model;

import java.util.List;

public class AlacarteModel {


    /**
     * alacarte : [{"alacarte_ID":1,"alacarte_Name":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true},{"alacarte_ID":1,"alacarte_Name":"sample string 1","price":1,"tax":"sample string 2","value":1,"tax_Amount":1,"is_select":true}]
     * status : sample string 1
     */

    private String status, total_Alacarte;
    private List<AlacarteDTO> alacarte;

    public String getTotal_Alacarte() {
        return total_Alacarte;
    }

    public void setTotal_Alacarte(String total_Alacarte) {
        this.total_Alacarte = total_Alacarte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AlacarteDTO> getAlacarte() {
        return alacarte;
    }

    public void setAlacarte(List<AlacarteDTO> alacarte) {
        this.alacarte = alacarte;
    }

    public static class AlacarteDTO {
        /**
         * alacarte_ID : 1
         * alacarte_Name : sample string 1
         * price : 1.0
         * tax : sample string 2
         * value : 1.0
         * tax_Amount : 1.0
         * is_select : true
         */

        private int alacarte_ID;
        private String alacarte_Name;
        private double price;
        private String tax;
        private double value;
        private double tax_Amount;
        private boolean is_select;

        public int getAlacarte_ID() {
            return alacarte_ID;
        }

        public void setAlacarte_ID(int alacarte_ID) {
            this.alacarte_ID = alacarte_ID;
        }

        public String getAlacarte_Name() {
            return alacarte_Name;
        }

        public void setAlacarte_Name(String alacarte_Name) {
            this.alacarte_Name = alacarte_Name;
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
