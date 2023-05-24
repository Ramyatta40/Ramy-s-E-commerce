package com.example.myecommerceproject;


import com.example.myecommerceproject.models.BannerItem;
import com.example.myecommerceproject.models.CategoryItem;
import com.example.myecommerceproject.models.ShopItem;
import com.example.myecommerceproject.models.UserModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
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
    @GET("getBannerImages.php")
    Call<List<BannerItem>> getImages();
    @GET("GetCategories.php")
    Call<List<CategoryItem>> getCategories();
    @Multipart
    @POST ("getShops.php")
    Call<List<ShopItem>> getShops(
            @Part("Id_categories") RequestBody id_categories
    );

}
