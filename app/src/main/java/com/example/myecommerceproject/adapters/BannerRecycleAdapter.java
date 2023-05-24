package com.example.myecommerceproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myecommerceproject.models.BannerItem;
import com.example.myecommerceproject.R;

import java.util.List;

public class BannerRecycleAdapter extends RecyclerView.Adapter<BannerRecycleAdapter.viewItem> {

    Context c;
    List<BannerItem> items;

    public BannerRecycleAdapter(Context c, List<BannerItem> items) {
        this.items = items;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {
        ImageView image;


        public viewItem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.banner_custom_layout);
            // text = itemView.findViewById(R.id.text);

        }
    }

    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_layout, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {

        Glide.with(c).load(items.get(position).getImageURL()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}