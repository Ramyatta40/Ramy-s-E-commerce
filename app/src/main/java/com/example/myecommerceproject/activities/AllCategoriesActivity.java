package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.adapters.AllCategoriesAdapter;
import com.example.myecommerceproject.adapters.CategorySelectAdapter;
import com.example.myecommerceproject.models.CategoryItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoriesActivity extends AppCompatActivity {
    ImageView back_all_cat;
    RecyclerView allCatRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        back_all_cat = findViewById(R.id.back_all_cat);
        allCatRec = findViewById(R.id.all_categories_recycler);
        allCatRec.setLayoutManager(new GridLayoutManager(this,2));
        back_all_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getCategoriesData();
    }
    public void getCategoriesData() {
        Api service = RetrofitClint.getApiService();
        Call<List<CategoryItem>> retCall = service.getCategories();
        retCall.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
//                BannerRecycleAdapter adapter = new BannerRecycleAdapter(HomeActivity.this,response.body());

                allCatRec.setAdapter(new AllCategoriesAdapter(AllCategoriesActivity.this, response.body()));

            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                Toast.makeText(AllCategoriesActivity.this, "Somthing wronge With getCategoriesData()", Toast.LENGTH_SHORT).show();
            }
        });

    }
}