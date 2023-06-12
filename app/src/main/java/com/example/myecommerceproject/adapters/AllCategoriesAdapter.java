package com.example.myecommerceproject.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.General;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.ShopInt;
import com.example.myecommerceproject.activities.HomeActivity;
import com.example.myecommerceproject.activities.LoginActivity;
import com.example.myecommerceproject.activities.ShopsCollectionsActivity;
import com.example.myecommerceproject.models.CategoryItem;
import com.example.myecommerceproject.models.ShopItem;
import com.example.myecommerceproject.models.UserModel;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.viewItem> {
    Context c;
    List<CategoryItem> items;

    public AllCategoriesAdapter(Context c, List<CategoryItem> items) {
        this.c = c;
        this.items = items;
    }

    class viewItem extends RecyclerView.ViewHolder {
        TextView textView;
        View view;

        public viewItem(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.all_cat_text);
            view = itemView.findViewById(R.id.view);
        }

    }

    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_categories_custom_list, parent, false);
        return new AllCategoriesAdapter.viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(AllCategoriesAdapter.viewItem holder, final int position) {
        holder.textView.setText(items.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent catIntent = new Intent(c, ShopsCollectionsActivity.class);
                catIntent.putExtra("Id_category",items.get(position).getId());
                c.startActivity(catIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
