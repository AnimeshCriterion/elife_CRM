package com.elifeindia.crm.model;

public class ChangePasswordModel {

    String message;
    String error_Massage;
    String id;

    @Override
    public String toString() {
        return "ChangePasswordModel{" +
                "message='" + message + '\'' +
                ", error_Massage='" + error_Massage + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

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
