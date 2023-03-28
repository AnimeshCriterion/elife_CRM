package com.elifeindia.crm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerStatusResponse {

    @SerializedName("connection_Status")
    @Expose
    private List<ConnectionStatus> connectionStatus = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<ConnectionStatus> getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(List<ConnectionStatus> connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public class ConnectionStatus {
        @SerializedName("connection_Status_ID")
        @Expose
        private Integer connectionStatusID;
        @SerializedName("status_Name")
        @Expose
        private String statusName;
        @SerializedName("company_ID")
        @Expose
        private Object companyID;

        public Integer getConnectionStatusID() {
            return connectionStatusID;
        }

        public void setConnectionStatusID(Integer connectionStatusID) {
            this.connectionStatusID = connectionStatusID;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public Object getCompanyID() {
            return companyID;
        }

        public void setCompanyID(Object companyID) {
            this.companyID = companyID;
        }

    }
}
