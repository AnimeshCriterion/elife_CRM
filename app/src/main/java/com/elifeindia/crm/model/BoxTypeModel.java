package com.elifeindia.crm.model;

import java.util.List;

public class BoxTypeModel {

    /**
     * box_Type : [{"boxType_ID":1,"boxTypeName":"sample string 1"},{"boxType_ID":1,"boxTypeName":"sample string 1"}]
     * status : sample string 1
     */

    private String status;
    private List<BoxTypeDTO> box_Type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BoxTypeDTO> getBox_Type() {
        return box_Type;
    }

    public void setBox_Type(List<BoxTypeDTO> box_Type) {
        this.box_Type = box_Type;
    }

    public static class BoxTypeDTO {
        /**
         * boxType_ID : 1
         * boxTypeName : sample string 1
         */

        private Integer boxType_ID;
        private String boxTypeName;

        public Integer getBoxType_ID() {
            return boxType_ID;
        }

        public void setBoxType_ID(Integer boxType_ID) {
            this.boxType_ID = boxType_ID;
        }

        public String getBoxTypeName() {
            return boxTypeName;
        }

        public void setBoxTypeName(String boxTypeName) {
            this.boxTypeName = boxTypeName;
        }
    }
}
