package com.example.myecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView bannerRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bannerRecycler = findViewById(R.id.banner_recycler);
        bannerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    public void getBannerImages() {
        Api service = RetrofitClint.getApiService();
        Call<List<BannerItem>> retCall = service.getImages();
        retCall.enqueue(new Callback<List<BannerItem>>() {
            @Override
            public void onResponse(Call<List<BannerItem>> call, Response<List<BannerItem>> response) {
bannerRecycler.setAdapter(new BannerRecycleAdapter(HomeActivity.this, response.body()));

            }

            @Override
            public void onFailure(Call<List<BannerItem>> call, Throwable t) {

            }
        });

    }
}