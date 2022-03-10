package com.elifeindia.crm.utils;

import java.util.ArrayList;
import java.util.List;

public class StaticAppData {
    public static List<String> filterDataList(){
        List<String> list = new ArrayList<>();
        list.add("All");
        list.add("A/c No");
        list.add("Sub ID");
        list.add("Box No");
        list.add("Mobile");
        list.add("Name");
        return list;
    }
}
