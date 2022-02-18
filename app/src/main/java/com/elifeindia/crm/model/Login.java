package com.elifeindia.crm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user_Id")
    @Expose
    private Integer userId;
    @SerializedName("user_Name")
    @Expose
    private String userName;
    @SerializedName("company_ID")
    @Expose
    private Integer companyID;
    @SerializedName("company_Name")
    @Expose
    private String companyName;
    @SerializedName("employee_ID")
    @Expose
    private Integer employeeID;
    @SerializedName("employee_Name")
    @Expose
    private String employeeName;
    @SerializedName("employee_Code")
    @Expose
    private String employeeCode;
    @SerializedName("role_Name")
    @Expose
    private String roleName;
    @SerializedName("role_Id")
    @Expose
    private Integer roleId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("role_Type")
    @Expose
    private String roleType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
