package com.nguyentinhdeveloper.baitaplab_2_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit.Api;
import com.nguyentinhdeveloper.baitaplab_2_androidnetworking.retrofit.DataAPI;

import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    private DataAPI dataAPI;
    private TextView hello;
    private TextView sumfood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");
        dataAPI = Api.getData();
        hello = (TextView) findViewById(R.id.hello);
        sumfood = (TextView) findViewById(R.id.sumfood);
        hello.setText(getIntent().getStringExtra("name"));
        dataAPI.getData().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Home.this, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                sumfood.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ERR", t.getMessage());
            }
        });

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
