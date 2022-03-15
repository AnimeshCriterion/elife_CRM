
package com.elifeindia.crm.model.generateinvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CableBoxwithSubscription {

    @SerializedName("boxAlacareteList")
    @Expose
    private BoxAlacareteList boxAlacareteList;
    @SerializedName("boxBouquetList")
    @Expose
    private BoxBouquetList boxBouquetList;
    @SerializedName("cableBox")
    @Expose
    private CableBox cableBox;

    public CableBoxwithSubscription(BoxAlacareteList boxAlacareteList, BoxBouquetList boxBouquetList, CableBox cableBox) {
        this.boxAlacareteList = boxAlacareteList;
        this.boxBouquetList = boxBouquetList;
        this.cableBox = cableBox;
    }
}
