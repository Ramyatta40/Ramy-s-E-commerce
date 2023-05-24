package com.example.myecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView bannerRecycler;
    RecyclerView categorySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bannerRecycler = findViewById(R.id.banner_recycler);
        categorySelect = findViewById(R.id.categories_select);
        bannerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categorySelect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getBannerImages();
        getCategoriesData();

    }

    public void getCategoriesData() {
        Api service = RetrofitClint.getApiService();
        Call<List<CategoryItem>> retCall = service.getCategories();
        retCall.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
//                BannerRecycleAdapter adapter = new BannerRecycleAdapter(HomeActivity.this,response.body());

                categorySelect.setAdapter(new CategorySelectAdapter(HomeActivity.this, response.body()));

            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Somthing wronge With getCategoriesData()", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getBannerImages() {
        Api service = RetrofitClint.getApiService();
        Call<List<BannerItem>> retCall = service.getImages();
        retCall.enqueue(new Callback<List<BannerItem>>() {
            @Override
            public void onResponse(Call<List<BannerItem>> call, Response<List<BannerItem>> response) {
//                BannerRecycleAdapter adapter = new BannerRecycleAdapter(HomeActivity.this,response.body());
                bannerRecycler.setAdapter(new BannerRecycleAdapter(HomeActivity.this, response.body()));

            }

            @Override
            public void onFailure(Call<List<BannerItem>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Somthing wronge With getBannerImages()", Toast.LENGTH_SHORT).show();
            }
        });

    }
}