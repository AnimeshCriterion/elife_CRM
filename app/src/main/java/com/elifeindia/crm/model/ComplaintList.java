package com.elifeindia.crm.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintList {

    @SerializedName("complaint")
    @Expose
    private List<Complaint> complaint = null;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @SerializedName("count")
    @Expose
    private String count;

    public List<Complaint> getComplaint() {
        return complaint;
    }

    public void setComplaint(List<Complaint> complaint) {
        this.complaint = complaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Complaint {

        @SerializedName("complaint_ID")
        @Expose
        private Integer complaintID;
        @SerializedName("complaint_Code")
        @Expose
        private String complaintCode;
        @SerializedName("company_ID")
        @Expose
        private Object companyID;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("customer_ID")
        @Expose
        private Integer customerID;
        @SerializedName("complaint_Date")
        @Expose
        private String complaintDate;
        @SerializedName("complaint_Type_ID")
        @Expose
        private Integer complaintTypeID;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("product_ID")
        @Expose
        private Integer productID;
        @SerializedName("assigned_Date")
        @Expose
        private String assignedDate;
        @SerializedName("employee_ID")
        @Expose
        private Integer employeeID;
        @SerializedName("assignTo")
        @Expose
        private String assignTo;
        @SerializedName("assignToContact")
        @Expose
        private Object assignToContact;
        @SerializedName("complaint_Status_ID")
        @Expose
        private Integer complaintStatusID;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("priority_ID")
        @Expose
        private Integer priorityID;
        @SerializedName("open_Date")
        @Expose
        private String openDate;
        @SerializedName("open_By")
        @Expose
        private Object openBy;
        @SerializedName("closed_Date")
        @Expose
        private String closedDate;
        @SerializedName("closed_By")
        @Expose
        private Object closedBy;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("account_No")
        @Expose
        private String accountNo;
        @SerializedName("subscriber_ID")
        @Expose
        private String subscriberID;
        @SerializedName("contact_No")
        @Expose
        private String contactNo;
        @SerializedName("area_Customer_ID")
        @Expose
        private String areaCustomerID;
        @SerializedName("areaName")
        @Expose
        private String areaName;
        @SerializedName("openby_Name")
        @Expose
        private String openbyName;
        @SerializedName("closeby_Name")
        @Expose
        private String closebyName;
        @SerializedName("complaint_Type")
        @Expose
        private String complaintType;
        @SerializedName("product_Name")
        @Expose
        private String productName;
        @SerializedName("complaint_Status")
        @Expose
        private String complaintStatus;
        @SerializedName("priority_Name")
        @Expose
        private String priorityName;
        @SerializedName("created_By")
        @Expose
        private String createdBy;
        @SerializedName("last_Update_By")
        @Expose
        private String lastUpdateBy;
        @SerializedName("last_Update_Date")
        @Expose
        private String lastUpdateDate;

        public Integer getComplaintID() {
            return complaintID;
        }

        public void setComplaintID(Integer complaintID) {
            this.complaintID = complaintID;
        }

        public String getComplaintCode() {
            return complaintCode;
        }

        public void setComplaintCode(String complaintCode) {
            this.complaintCode = complaintCode;
        }

        public Object getCompanyID() {
            return companyID;
        }

        public void setCompanyID(Object companyID) {
            this.companyID = companyID;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getCustomerID() {
            return customerID;
        }

        public void setCustomerID(Integer customerID) {
            this.customerID = customerID;
        }

        public String getComplaintDate() {
            return complaintDate;
        }

        public void setComplaintDate(String complaintDate) {
            this.complaintDate = complaintDate;
        }

        public Integer getComplaintTypeID() {
            return complaintTypeID;
        }

        public void setComplaintTypeID(Integer complaintTypeID) {
            this.complaintTypeID = complaintTypeID;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getProductID() {
            return productID;
        }

        public void setProductID(Integer productID) {
            this.productID = productID;
        }

        public String getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(String assignedDate) {
            this.assignedDate = assignedDate;
        }

        public Integer getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(Integer employeeID) {
            this.employeeID = employeeID;
        }

        public String getAssignTo() {
            return assignTo;
        }

        public void setAssignTo(String assignTo) {
            this.assignTo = assignTo;
        }

        public Object getAssignToContact() {
            return assignToContact;
        }

        public void setAssignToContact(Object assignToContact) {
            this.assignToContact = assignToContact;
        }

        public Integer getComplaintStatusID() {
            return complaintStatusID;
        }

        public void setComplaintStatusID(Integer complaintStatusID) {
            this.complaintStatusID = complaintStatusID;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Integer getPriorityID() {
            return priorityID;
        }

        public void setPriorityID(Integer priorityID) {
            this.priorityID = priorityID;
        }

        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }

        public Object getOpenBy() {
            return openBy;
        }

        public void setOpenBy(Object openBy) {
            this.openBy = openBy;
        }

        public String getClosedDate() {
            return closedDate;
        }

        public void setClosedDate(String closedDate) {
            this.closedDate = closedDate;
        }

        public Object getClosedBy() {
            return closedBy;
        }

        public void setClosedBy(Object closedBy) {
            this.closedBy = closedBy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getSubscriberID() {
            return subscriberID;
        }

        public void setSubscriberID(String subscriberID) {
            this.subscriberID = subscriberID;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getAreaCustomerID() {
            return areaCustomerID;
        }

        public void setAreaCustomerID(String areaCustomerID) {
            this.areaCustomerID = areaCustomerID;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getOpenbyName() {
            return openbyName;
        }

        public void setOpenbyName(String openbyName) {
            this.openbyName = openbyName;
        }

        public String getClosebyName() {
            return closebyName;
        }

        public void setClosebyName(String closebyName) {
            this.closebyName = closebyName;
        }

        public String getComplaintType() {
            return complaintType;
        }

        public void setComplaintType(String complaintType) {
            this.complaintType = complaintType;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getComplaintStatus() {
            return complaintStatus;
        }

        public void setComplaintStatus(String complaintStatus) {
            this.complaintStatus = complaintStatus;
        }

        public String getPriorityName() {
            return priorityName;
        }

        public void setPriorityName(String priorityName) {
            this.priorityName = priorityName;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        @Override
        public String toString() {
            return "Complaint{" +
                    "complaintID=" + complaintID +
                    ", complaintCode='" + complaintCode + '\'' +
                    ", companyID=" + companyID +
                    ", address='" + address + '\'' +
                    ", customerID=" + customerID +
                    ", complaintDate='" + complaintDate + '\'' +
                    ", complaintTypeID=" + complaintTypeID +
                    ", description='" + description + '\'' +
                    ", productID=" + productID +
                    ", assignedDate='" + assignedDate + '\'' +
                    ", employeeID=" + employeeID +
                    ", assignTo='" + assignTo + '\'' +
                    ", assignToContact=" + assignToContact +
                    ", complaintStatusID=" + complaintStatusID +
                    ", comment='" + comment + '\'' +
                    ", priorityID=" + priorityID +
                    ", openDate='" + openDate + '\'' +
                    ", openBy=" + openBy +
                    ", closedDate='" + closedDate + '\'' +
                    ", closedBy=" + closedBy +
                    ", name='" + name + '\'' +
                    ", accountNo='" + accountNo + '\'' +
                    ", subscriberID='" + subscriberID + '\'' +
                    ", contactNo='" + contactNo + '\'' +
                    ", areaCustomerID='" + areaCustomerID + '\'' +
                    ", areaName='" + areaName + '\'' +
                    ", openbyName='" + openbyName + '\'' +
                    ", closebyName='" + closebyName + '\'' +
                    ", complaintType='" + complaintType + '\'' +
                    ", productName='" + productName + '\'' +
                    ", complaintStatus='" + complaintStatus + '\'' +
                    ", priorityName='" + priorityName + '\'' +
                    ", createdBy='" + createdBy + '\'' +
                    ", lastUpdateBy='" + lastUpdateBy + '\'' +
                    ", lastUpdateDate='" + lastUpdateDate + '\'' +
                    '}';
        }

        public String getLastUpdateBy() {
            return lastUpdateBy;
        }

        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        public String getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(String lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

    }
}
