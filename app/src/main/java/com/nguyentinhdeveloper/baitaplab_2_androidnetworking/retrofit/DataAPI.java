package com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataAPI {


   //Đăng nhập
    @FormUrlEncoded
    @POST("/android/login.php")
    Call<String> login(@Field("username")String email, @Field("password")String password);


    // Get all dữ liệu
    @GET("/android/bai1.php?food=today")
    Call<String> getData();



}
