package com.example.newtask.Global;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.newtask.GetData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance extends Application {
    private static RetrofitClientInstance mInstance;
   // public static String BASE_URL = "https://192.168.0.104:8080/";
    public static String BASE_URL = "http://mobicraftsv2.com";
    static GetData service;

    //http://mobicraftsv2.com/bloc49/api/v3/product-details?product_id=1812&lang=en&store=KW

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized RetrofitClientInstance getInstance() {
        return mInstance;
    }

    public static GetData getWebservice(Context context) {

        if (!isNetworkConnected(context)) {

            Toast.makeText(context, "No internet connection!!", Toast.LENGTH_SHORT).show();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GetData.class);
        return service;
    }


    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();

    }

}

