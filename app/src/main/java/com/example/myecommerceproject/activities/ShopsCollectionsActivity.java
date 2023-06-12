package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.ShopInt;
import com.example.myecommerceproject.adapters.ShopsAdapter;
import com.example.myecommerceproject.models.ShopItem;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsCollectionsActivity extends AppCompatActivity {
    RecyclerView shopsCollectionRec;
    String Id_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_collections);
        shopsCollectionRec = findViewById(R.id.shops_collection_rec);
        shopsCollectionRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent intent = getIntent();
        Id_category = intent.getStringExtra("Id_category");
getShops(Id_category);

    }
    public void getShops(String id_categories) {


        Api service = RetrofitClint.getApiService();

        RequestBody id_categoriesRequestBody = RequestBody.create(MediaType.parse("text/plain"), id_categories);

        Call<List<ShopItem>> retCall = service.getShops(id_categoriesRequestBody);
        retCall.enqueue(new Callback<List<ShopItem>>() {
            @Override

            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
//                ShopInt shopInt = (HomeActivity) c;
//                shopInt.showShop(response.body());
                shopsCollectionRec.setAdapter(new ShopsAdapter(ShopsCollectionsActivity.this,response.body()));
            }

            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable t) {


            }
        });

    }

}