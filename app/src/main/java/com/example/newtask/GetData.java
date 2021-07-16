package com.example.newtask;

import com.example.newtask.module.ProductModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {

    @GET("/bloc49/api/v3/product-details?")
    Call<ProductModule.Example> getProductDetail(@Query("product_id") String product_id,
                                         @Query("lang") String lang,
                                         @Query("store") String store);

}
