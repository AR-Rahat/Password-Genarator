package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private  static int sto=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //Intent in = new Intent(this,first_time_show.class);
        // startActivity(in);


        setContentView(R.layout.activity_main);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent homeIntent=new Intent(MainActivity.this, Homepage.class);
            startActivity(homeIntent);
            finish();
            }
        },sto);
    }
}