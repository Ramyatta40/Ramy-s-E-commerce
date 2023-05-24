package com.example.myecommerceproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
