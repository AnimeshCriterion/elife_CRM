package com.elifeindia.crm.model;

import java.util.List;

public class FooterModel {


    /**
     * headerfooter : [{"show_Type":"sample string 1","display_Content":"sample string 2"},{"show_Type":"sample string 1","display_Content":"sample string 2"}]
     * is_show : true
     */

    private boolean is_show;
    private List<HeaderfooterDTO> headerfooter;

    public boolean isIs_show() {
        return is_show;
    }

    public void setIs_show(boolean is_show) {
        this.is_show = is_show;
    }

    public List<HeaderfooterDTO> getHeaderfooter() {
        return headerfooter;
    }

    public void setHeaderfooter(List<HeaderfooterDTO> headerfooter) {
        this.headerfooter = headerfooter;
    }

    public static class HeaderfooterDTO {
        /**
         * show_Type : sample string 1
         * display_Content : sample string 2
         */

        private String show_Type;
        private String display_Content;

        public String getShow_Type() {
            return show_Type;
        }

        public void setShow_Type(String show_Type) {
            this.show_Type = show_Type;
        }

        public String getDisplay_Content() {
            return display_Content;
        }

        public void setDisplay_Content(String display_Content) {
            this.display_Content = display_Content;
        }
    }
}
