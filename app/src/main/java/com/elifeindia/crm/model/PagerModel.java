package com.elifeindia.crm.model;

import java.util.List;

public class PagerModel {
    private List<PageNo> page_no = null;

    public List<PageNo> getPage_no() {
        return page_no;
    }

    public void setPage_no(List<PageNo> page_no) {
        this.page_no = page_no;
    }

    public static class PageNo{
        int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
