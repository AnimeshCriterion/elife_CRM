package com.elifeindia.crm.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaResponse {

    @SerializedName("area")
    @Expose
    private List<Area> area = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Area> getArea() {
        return area;
    }

    public void setArea(List<Area> area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public class Area {

        @SerializedName("area_ID")
        @Expose
        private Integer areaID;
        @SerializedName("areaName")
        @Expose
        private String areaName;
        @SerializedName("company_ID")
        @Expose
        private Integer companyID;
        @SerializedName("creation_Date")
        @Expose
        private String creationDate;
        @SerializedName("created_By")
        @Expose
        private Integer createdBy;
        @SerializedName("last_Updated_Date")
        @Expose
        private String lastUpdatedDate;
        @SerializedName("last_Updated_By")
        @Expose
        private Integer lastUpdatedBy;
        @SerializedName("delete_Date")
        @Expose
        private String deleteDate;
        @SerializedName("user_ID")
        @Expose
        private Integer userID;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("delete_Flag")
        @Expose
        private Boolean deleteFlag;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getAreaID() {
            return areaID;
        }

        public void setAreaID(Integer areaID) {
            this.areaID = areaID;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public Integer getCompanyID() {
            return companyID;
        }

        public void setCompanyID(Integer companyID) {
            this.companyID = companyID;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public Integer getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(Integer lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public String getDeleteDate() {
            return deleteDate;
        }

        public void setDeleteDate(String deleteDate) {
            this.deleteDate = deleteDate;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Boolean getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(Boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
