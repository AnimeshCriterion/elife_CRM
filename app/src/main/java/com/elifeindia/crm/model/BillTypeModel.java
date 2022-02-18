package com.elifeindia.crm.model;

import java.util.List;

public class BillTypeModel {

    /**
     * billType : [{"bill_Type_ID":1,"bill_Type":"sample string 1"},{"bill_Type_ID":1,"bill_Type":"sample string 1"}]
     * status : sample string 1
     */

    private String status;
    private List<BillTypeDTO> billType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BillTypeDTO> getBillType() {
        return billType;
    }

    public void setBillType(List<BillTypeDTO> billType) {
        this.billType = billType;
    }

    public static class BillTypeDTO {
        /**
         * bill_Type_ID : 1
         * bill_Type : sample string 1
         */

        private Integer bill_Type_ID;
        private String bill_Type;

        public Integer getBill_Type_ID() {
            return bill_Type_ID;
        }

        public void setBill_Type_ID(Integer bill_Type_ID) {
            this.bill_Type_ID = bill_Type_ID;
        }

        public String getBill_Type() {
            return bill_Type;
        }

        public void setBill_Type(String bill_Type) {
            this.bill_Type = bill_Type;
        }
    }
}
