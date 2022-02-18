package com.elifeindia.crm.model;

import java.util.List;

public class PaymentStatusModel {


    /**
     * paymentstatus : [{"status_ID":1,"payment_Status":"UnPaid"},{"status_ID":2,"payment_Status":"Partial Paid"},{"status_ID":3,"payment_Status":"Paid"},{"status_ID":4,"payment_Status":"Partial Forwarded"},{"status_ID":5,"payment_Status":"Unpaid Forwarded"}]
     * status : null
     */

    private Object status;
    private List<PaymentstatusDTO> paymentstatus;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public List<PaymentstatusDTO> getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(List<PaymentstatusDTO> paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public static class PaymentstatusDTO {
        /**
         * status_ID : 1
         * payment_Status : UnPaid
         */

        private String status_ID;
        private String payment_Status;

        public String getStatus_ID() {
            return status_ID;
        }

        public void setStatus_ID(String status_ID) {
            this.status_ID = status_ID;
        }

        public String getPayment_Status() {
            return payment_Status;
        }

        public void setPayment_Status(String payment_Status) {
            this.payment_Status = payment_Status;
        }
    }
}
