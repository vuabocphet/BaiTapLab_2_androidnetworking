package com.nguyentinhdeveloper.baitaplab_2_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit.Api;
import com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit.Cleint;
import com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit.DataAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private EditText nameA;
    private DataAPI dataAPI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LOGIN");
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        nameA = (EditText) findViewById(R.id.name_a);
        dataAPI= Api.getData();
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }


    private void login(){

        String username=name.getText().toString().trim();
        String password=pass.getText().toString().trim();
        final String name_a=nameA.getText().toString().trim();

        if (username.equals("")){
            name.setError("Không để trống");
            return;
        }

        if (password.equals("")){
            pass.setError("Không để trống");
            return;
        }

        if (name_a.equals("")){
            nameA.setError("Không để trống");
            return;
        }

        dataAPI.login(username,password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Login.this, "Code:"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.e("DATA",response.body());

                if (response.body().startsWith("Xin chào")){
                    Intent intent=new Intent(Login.this,Home.class);
                    intent.putExtra("name",response.body()+name_a);
                    startActivity(intent);
                    finish();

                }

                else

                    Toast.makeText(Login.this, response.body(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Login.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.e("ERR",t.getMessage());
            }
        });

    }
}
