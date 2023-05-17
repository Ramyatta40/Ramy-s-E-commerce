package com.example.myecommerceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView log_in;
    CountryCodePicker countryCodePicker2;
    EditText phoneInput;
    EditText nameInput;
    EditText emailInput;
    EditText passwordConf;
    EditText password2;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        log_in = findViewById(R.id.log_in);
        countryCodePicker2 = findViewById(R.id.countyCodePicker2);
        phoneInput = findViewById(R.id.phoneInput2);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordConf = findViewById(R.id.passwordconf);
        password2 = findViewById(R.id.password2);
        registerBtn = findViewById(R.id.registerBtn);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();


        RequestBody phoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), phoneInput.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password2.getText().toString());
        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), countryCodePicker2.getSelectedCountryCode());
        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), nameInput.getText().toString());
        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), emailInput.getText().toString());

        Call<UserModel> retCall = service.signUp(phoneRequestBody, passwordRequestBody, conCodeRequestBody, emailRequestBody, nameRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override

            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    Toast.makeText(Register.this, "Done", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Register.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();

            }
        });

    }
}