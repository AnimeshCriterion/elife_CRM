package com.elifeindia.crm.model;

public class UpdateBox {


    /**
     * iu : {"message":"sample string 1","error_Massage":"sample string 2","id":"sample string 3"}
     * activation_Date : sample string 1
     * expiry_Date : sample string 2
     * box_Amount : sample string 3
     */

    private IuDTO iu;
    private String activation_Date;
    private String expiry_Date;
    private String box_Amount;

    public IuDTO getIu() {
        return iu;
    }

    public void setIu(IuDTO iu) {
        this.iu = iu;
    }

    public String getActivation_Date() {
        return activation_Date;
    }

    public void setActivation_Date(String activation_Date) {
        this.activation_Date = activation_Date;
    }

    public String getExpiry_Date() {
        return expiry_Date;
    }

    public void setExpiry_Date(String expiry_Date) {
        this.expiry_Date = expiry_Date;
    }

    public String getBox_Amount() {
        return box_Amount;
    }

    public void setBox_Amount(String box_Amount) {
        this.box_Amount = box_Amount;
    }

    public static class IuDTO {
        /**
         * message : sample string 1
         * error_Massage : sample string 2
         * id : sample string 3
         */

        private String message;
        private String error_Massage;
        private String id;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getError_Massage() {
            return error_Massage;
        }

        public void setError_Massage(String error_Massage) {
            this.error_Massage = error_Massage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
