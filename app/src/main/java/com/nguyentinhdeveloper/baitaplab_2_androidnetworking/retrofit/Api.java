package com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit;

public class Api {

    public static final String Base_Url="http://dotplays.com";
    public static DataAPI getData(){
        return Cleint.getClient(Base_Url).create(DataAPI.class);
    }
}
