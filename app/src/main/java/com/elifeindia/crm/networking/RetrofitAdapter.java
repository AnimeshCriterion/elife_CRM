package com.elifeindia.crm.networking;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private static Retrofit retrofit;
    private static Gson gson;
    //private static final String BASE_URL = "http://newcrmapitest.elifeindia.in/api/";
    private static final String BASE_URL = "http://crmprodtestapi.elifeindia.in/api/";
   // private static final String BASE_URL = "http://livecrmtestapi.elifeindia.in/api/";



    public static final String GET_COMPLAINT = BASE_URL + "Values/GetComplaint?Complaint_ID=";

    public static final String OPEN_CLOSE = BASE_URL + "Values/OpencloseComplaint";

    public static final String UPDATE_CUSTOMER = BASE_URL + "Values/Update_Customer";

    public static synchronized Retrofit getInstance() {

        if (retrofit == null) {
            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getHeader())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }

        return retrofit;
    }

    public static OkHttpClient getHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
//                .addNetworkInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request request = null;
//                                if (authorizationValue != null) {
//                                    Log.d("--Authorization-- ", authorizationValue);
//
//                                    Request original = chain.request();
//                                    // Request customization: add request headers
//                                    Request.Builder requestBuilder = original.newBuilder()
//                                            .addHeader("Authorization", authorizationValue);
//
//                                    request = requestBuilder.build();
//                                }
//                                return chain.proceed(request);
//                            }
//                        })
                .build();
        return okClient;

    }
}


