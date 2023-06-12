package com.example.myecommerceproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.models.CategoryItem;
import com.example.myecommerceproject.models.ShopItem;

import java.util.List;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.viewItem> {
    Context c;
    List<ShopItem> items;


    public ShopsAdapter(Context c, List<ShopItem> items) {
        this.c = c;
        this.items = items;
    }
    class viewItem extends RecyclerView.ViewHolder {
       // Button button;
        ImageView imageView;
        TextView shopName;
        public viewItem(View itemView) {
            super(itemView);
           // button = itemView.findViewById(R.id.select_btn);
            imageView = itemView.findViewById(R.id.shop_image_home);
            shopName = itemView.findViewById(R.id.shop_name);
        }

    }

    @Override
    public ShopsAdapter.viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_list_home, parent, false);
        return new ShopsAdapter.viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(ShopsAdapter.viewItem holder, final int position) {
        Glide.with(c).load(items.get(position).getImageURL()).into(holder.imageView);
        holder.shopName.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
