package com.elifeindia.crm.networking;


public class NetworkUtils {

    private static ApiService apiService;

    public static ApiService getUserApiInstance() {
        if (apiService == null)
            apiService = RetrofitAdapter.getInstance().create(ApiService.class);
        return apiService;
    }

}
