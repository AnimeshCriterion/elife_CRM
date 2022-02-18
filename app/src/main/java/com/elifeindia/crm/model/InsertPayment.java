package com.elifeindia.crm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertPayment {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("error_Massage")
    @Expose
    public String errorMassage;
    @SerializedName("id")
    @Expose
    public String id;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
