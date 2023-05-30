package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.ShopInt;
import com.example.myecommerceproject.adapters.ShopsAdapter;
import com.example.myecommerceproject.models.BannerItem;
import com.example.myecommerceproject.adapters.BannerRecycleAdapter;
import com.example.myecommerceproject.models.CategoryItem;
import com.example.myecommerceproject.adapters.CategorySelectAdapter;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.models.ShopItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ShopInt {
    RecyclerView bannerRecycler;
    RecyclerView categorySelect;
    TextView see_all_cat;
    RecyclerView shops_rec_home ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        see_all_cat = findViewById(R.id.see_all_cat);
        bannerRecycler = findViewById(R.id.banner_recycler);
        categorySelect = findViewById(R.id.categories_select);
        shops_rec_home = findViewById(R.id.shops_rec_home);
        bannerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categorySelect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        shops_rec_home.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        see_all_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllCategoriesActivity.class);
                startActivity(intent);
            }
        });

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

    @Override
    public void showShop(List<ShopItem> shopItemList) {
        shops_rec_home.setAdapter(new ShopsAdapter(HomeActivity.this,shopItemList));
    }
}