package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ComplaintTypeList {

    @SerializedName("complaintType")
    @Expose
    public List<ComplaintType> complaintType = null;
    @SerializedName("status")
    @Expose
    public String status;

    public List<ComplaintType> getComplaintType() {
        return complaintType;
    }

    public String getStatus() {
        return status;
    }

    public static class ComplaintType {

        @SerializedName("complaint_Type_ID")
        @Expose
        public Integer complaintTypeID;
        @SerializedName("complaint_Type")
        @Expose
        public String complaintType;


        public Integer getComplaintTypeID() {
            return complaintTypeID;
        }

        public String getComplaintType() {
            return complaintType;
        }
    }
}
