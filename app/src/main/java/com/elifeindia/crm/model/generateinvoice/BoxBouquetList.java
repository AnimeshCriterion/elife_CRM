
package com.elifeindia.crm.model.generateinvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoxBouquetList {

    @SerializedName("boxBouquet")
    @Expose
    private List<BoxBouquet> boxBouquet = null;

    public List<BoxBouquet> getBoxBouquet() {
        return boxBouquet;
    }

    public void setBoxBouquet(List<BoxBouquet> boxBouquet) {
        this.boxBouquet = boxBouquet;
    }

}
