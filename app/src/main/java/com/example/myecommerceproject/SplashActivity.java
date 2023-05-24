package com.example.myecommerceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
                String Id = General.getPreferenceValue(SplashActivity.this,"Id","");
                if (Id != ""){
                    startActivity(intent2);
                }
                else {
                    startActivity(intent);
                }


                finish();
            }
        }, 3000);
    }

}