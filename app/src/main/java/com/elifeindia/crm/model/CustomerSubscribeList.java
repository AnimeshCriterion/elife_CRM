package com.elifeindia.crm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CustomerSubscribeList {

    @SerializedName("cus_subscribeType")
    @Expose
    private List<CusSubscribeType> cusSubscribeType = null;
    @SerializedName("status")
    @Expose
    private Object status;

    public List<CusSubscribeType> getCusSubscribeType() {
        return cusSubscribeType;
    }

    public void setCusSubscribeType(List<CusSubscribeType> cusSubscribeType) {
        this.cusSubscribeType = cusSubscribeType;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
    public class CusSubscribeType {

        @SerializedName("triple_play_ID")
        @Expose
        private Integer triplePlayID;
        @SerializedName("triple_play")
        @Expose
        private String triplePlay;

        public Integer getTriplePlayID() {
            return triplePlayID;
        }

        public void setTriplePlayID(Integer triplePlayID) {
            this.triplePlayID = triplePlayID;
        }

        public String getTriplePlay() {
            return triplePlay;
        }

        public void setTriplePlay(String triplePlay) {
            this.triplePlay = triplePlay;
        }

    }

}
