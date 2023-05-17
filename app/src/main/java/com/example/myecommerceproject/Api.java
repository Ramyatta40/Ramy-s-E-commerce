package com.example.myecommerceproject;

import android.app.DownloadManager;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @Multipart
    @POST("login.php")
    Call<UserModel> login(@Part("Phone") RequestBody phone,
                          @Part("Password") RequestBody password,
                          @Part("ConCode") RequestBody conCode
    );
    @Multipart
    @POST("SignUp.php")
    Call<UserModel> signUp(@Part("Phone") RequestBody phone,
                          @Part("Password") RequestBody password,
                          @Part("ConCode") RequestBody conCode,
                          @Part("Name") RequestBody name,
                          @Part("Email") RequestBody email
    );


}
