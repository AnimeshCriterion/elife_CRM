package com.elifeindia.crm.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeList {

    @SerializedName("employee")
    @Expose
    private List<Employee> employee = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public class Employee {

        @SerializedName("employee_ID")
        @Expose
        private Integer employeeID;
        @SerializedName("employee_Code")
        @Expose
        private String employeeCode;
        @SerializedName("employee_Name")
        @Expose
        private String employeeName;
        @SerializedName("contact_No")
        @Expose
        private String contactNo;
        @SerializedName("employee_Type_ID")
        @Expose
        private Integer employeeTypeID;
        @SerializedName("employee_Type")
        @Expose
        private String employeeType;
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

        public Integer getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(Integer employeeID) {
            this.employeeID = employeeID;
        }

        public String getEmployeeCode() {
            return employeeCode;
        }

        public void setEmployeeCode(String employeeCode) {
            this.employeeCode = employeeCode;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public Integer getEmployeeTypeID() {
            return employeeTypeID;
        }

        public void setEmployeeTypeID(Integer employeeTypeID) {
            this.employeeTypeID = employeeTypeID;
        }

        public String getEmployeeType() {
            return employeeType;
        }

        public void setEmployeeType(String employeeType) {
            this.employeeType = employeeType;
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

