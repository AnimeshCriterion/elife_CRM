package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintStatusList {

    @SerializedName("complaintStatus")
    @Expose
    public List<Complaintstatus> complaintStatus = null;
    @SerializedName("status")
    @Expose
    public String status;

    public List<Complaintstatus> getComplaintStatus() {
        return complaintStatus;
    }

    public String getStatus() {
        return status;
    }

    public static class Complaintstatus {

        @SerializedName("complaint_Status_ID")
        @Expose
        public Integer complaintStatusID;
        @SerializedName("complaint_Status")
        @Expose
        public String complaintStatus;

        public Integer getComplaintStatusID() {
            return complaintStatusID;
        }

        public String getComplaintStatus() {
            return complaintStatus;
        }
    }
}
