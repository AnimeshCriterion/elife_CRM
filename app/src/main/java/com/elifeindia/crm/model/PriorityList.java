package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriorityList {

    @SerializedName("priority")
    @Expose
    private List<Priority> priority = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Priority> getPriority() {
        return priority;
    }

    public void setPriority(List<Priority> priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Priority {

        @SerializedName("priority_ID")
        @Expose
        private Integer priorityID;
        @SerializedName("priority_Name")
        @Expose
        private String priorityName;

        public Integer getPriorityID() {
            return priorityID;
        }

        public void setPriorityID(Integer priorityID) {
            this.priorityID = priorityID;
        }

        public String getPriorityName() {
            return priorityName;
        }

        public void setPriorityName(String priorityName) {
            this.priorityName = priorityName;
        }

    }
}



