package com.example.myecommerceproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.ShopInt;
import com.example.myecommerceproject.activities.HomeActivity;
import com.example.myecommerceproject.models.CategoryItem;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.models.ShopItem;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySelectAdapter extends RecyclerView.Adapter<CategorySelectAdapter.viewItem> {
    Context c;
    List<CategoryItem> items;

    public CategorySelectAdapter(Context c, List<CategoryItem> items) {
        this.c = c;
        this.items = items;
    }

    class viewItem extends RecyclerView.ViewHolder {
        Button button;

        public viewItem(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.select_btn);
        }

    }

    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_select_rec, parent, false);
        return new CategorySelectAdapter.viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(CategorySelectAdapter.viewItem holder, final int position) {
        holder.button.setText(items.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getShops(items.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void getShops(String id_categories) {


        Api service = RetrofitClint.getApiService();

        RequestBody id_categoriesRequestBody = RequestBody.create(MediaType.parse("text/plain"), id_categories);

        Call<List<ShopItem>> retCall = service.getShops(id_categoriesRequestBody);
        retCall.enqueue(new Callback<List<ShopItem>>() {
            @Override

            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                ShopInt shopInt = (HomeActivity) c;
                shopInt.showShop(response.body());

            }

            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable t) {


            }
        });

    }
}
