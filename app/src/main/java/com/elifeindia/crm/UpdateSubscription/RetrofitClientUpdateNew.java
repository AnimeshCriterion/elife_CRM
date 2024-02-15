package com.elifeindia.crm.UpdateSubscription;


import com.elifeindia.crm.networking.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public   class RetrofitClientUpdateNew {


    //String API_BASE_URL ="http://107.23.122.121:1021/";
    //String API_BASE_URL ="http://3.220.157.39:4001/";
   // String API_BASE_URL = "http://crmprodtestapi.elifeindia.in/api/";
    String API_BASE_URL = "http://livecrmtestapi.elifeindia.in/api/";
  // String API_BASE_URL = "https://testapi.hapini.in";
    //String API_BASE_URL ="http://t-api.hapini.in";//(Dev)

    private static RetrofitClientUpdateNew mInstance;
    private final Retrofit retrofit;

    private RetrofitClientUpdateNew() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .writeTimeout(5, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(logging).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClientUpdateNew getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientUpdateNew();
        }
        return mInstance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);
    }


}
