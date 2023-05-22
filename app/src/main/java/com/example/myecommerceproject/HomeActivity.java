package com.example.myecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {
RecyclerView bannerRecycler;
ArrayList<BannerItem> bannerItemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bannerRecycler= findViewById(R.id.banner_recycler);
        bannerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
    public void getBannerImages(){
        Api service = RetrofitClint.getApiService();
        Call<List<BannerItem>> retCall= service.getImages();


    }
}