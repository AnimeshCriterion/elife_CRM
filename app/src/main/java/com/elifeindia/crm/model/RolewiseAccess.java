package com.elifeindia.crm.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RolewiseAccess {

    @SerializedName("access")
    @Expose
    private List<Access> access = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Access> getAccess() {
        return access;
    }

    public void setAccess(List<Access> access) {
        this.access = access;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public class Access {

        @SerializedName("approve")
        @Expose
        private Boolean approve;
        @SerializedName("module")
        @Expose
        private String module;
        @SerializedName("screen_Name")
        @Expose
        private String screenName;

        public Boolean getApprove() {
            return approve;
        }

        public void setApprove(Boolean approve) {
            this.approve = approve;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

    }

}
