package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myecommerceproject.General;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.models.UserModel;

public class SplashActivity extends AppCompatActivity {
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon = findViewById(R.id.imageView);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.blink);
        icon.startAnimation(a);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                Intent intent2 = new Intent(SplashActivity.this, HomeActivity.class);
                String Id = General.getPreferenceValue(SplashActivity.this, "Id", "");
                if (Id != "") {
//                    UserModel.currentUser.setId(Id);
//                    UserModel.currentUser.setName(General.getPreferenceValue(SplashActivity.this, "Name", ""));
//                    UserModel.currentUser.setConCode(General.getPreferenceValue(SplashActivity.this, "ConCode", ""));
//                    UserModel.currentUser.setPhone( General.getPreferenceValue(SplashActivity.this, "Phone", "") );
//                    UserModel.currentUser.setEmail( General.getPreferenceValue(SplashActivity.this, "Email", "") );
                    startActivity(intent2);
                } else {
                    startActivity(intent);
                }


                finish();
            }
        }, 2000);
    }

}