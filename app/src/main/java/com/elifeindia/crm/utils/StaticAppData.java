package com.elifeindia.crm.utils;

import java.util.ArrayList;
import java.util.List;

public class StaticAppData {
    public static List<String> filterDataList(){
        List<String> list = new ArrayList<>();
        list.add("All");
        list.add("Name");
        list.add("A/C No");
        list.add("Contact No");
        list.add("Subscriber ID");
        list.add("Box No");
        return list;
    }
}
