package com.example.itemslistgl.Interface;

import com.example.itemslistgl.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("list")
    Call<List<Item>> getItem();

}
