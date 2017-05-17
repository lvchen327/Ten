package com.example.administrator.myapplication.api;

import com.example.administrator.myapplication.bean.CriticBean;
import com.example.administrator.myapplication.bean.CriticContentBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/5/17.
 */

public interface ApiService {
    public static final String  BASE_URL="http://api.shigeten.net";
    //影评电影
    // /api/Critic/GetCriticList
    @GET("/api/Critic/GetCriticList")
    Call<CriticBean> getCriticBean();
    //影评内容
    // /api/Critic/GetCriticContent?id=111
    @GET("/api/Critic/GetCriticContent")
    Call<CriticContentBean> getCriticContentBean(@Query("id") int id);
}
