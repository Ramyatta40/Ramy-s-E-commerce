package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myecommerceproject.R;

public class AllCategoriesActivity extends AppCompatActivity {
ImageView back_all_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        back_all_cat= findViewById(R.id.back_all_cat);
        back_all_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}