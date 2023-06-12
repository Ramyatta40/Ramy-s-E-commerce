package com.example.myecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myecommerceproject.Api;
import com.example.myecommerceproject.General;
import com.example.myecommerceproject.R;
import com.example.myecommerceproject.RetrofitClint;
import com.example.myecommerceproject.models.UserModel;
import com.hbb20.CountryCodePicker;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView createAccount;
    CountryCodePicker countryCodePicker;
    EditText phoneNum;
    EditText password;
    Button login;
    //public UserModel currentUser;
    boolean isRememberChecked;
    CheckBox rememberCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createAccount = findViewById(R.id.createAccount);
        countryCodePicker = findViewById(R.id.countyCodePicker);
        phoneNum = findViewById(R.id.phoneInput);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        rememberCheck = findViewById(R.id.remember_check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        rememberCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                isRememberChecked = isChecked;
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void login() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();

        RequestBody phoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), phoneNum.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());
        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), countryCodePicker.getSelectedCountryCode());
        Call<UserModel> retCall = service.login(phoneRequestBody, passwordRequestBody, conCodeRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override

            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                    UserModel.currentUser = response.body();
                    if (isRememberChecked) {
                        General.addToSharedPreference(LoginActivity.this, "Id", response.body().getId());
                        General.addToSharedPreference(LoginActivity.this, "Name", response.body().getName());
                        General.addToSharedPreference(LoginActivity.this, "ConCode", response.body().getConCode());
                        General.addToSharedPreference(LoginActivity.this, "Phone", response.body().getPhone());
                        General.addToSharedPreference(LoginActivity.this, "Email", response.body().getEmail());

                    } else {
                        General.addToSharedPreference(LoginActivity.this, "Id", "");
                    }
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "somthing wronge", Toast.LENGTH_LONG).show();
            }
        });

    }
}