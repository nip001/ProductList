package com.batch9.productlist.service;

import android.widget.ImageView;

import com.batch9.productlist.entity.Product;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProductInterface {

    @GET("/product/")
    Call<ArrayList<Product>> getAll();

    @Multipart
    @POST("/product/add")
    Call<String> addProduct(@Part("file") RequestBody images, @Part("data") String data);
}
