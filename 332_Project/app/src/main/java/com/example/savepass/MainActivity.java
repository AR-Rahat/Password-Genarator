package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=preferences.getString("remember","");
        if (checkbox.equals("true"))
        {
            Intent homeIntent=new Intent(MainActivity.this, login_page.class);
            startActivity(homeIntent);
            finish();
        }
        else if(checkbox.equals("false"))
        {
            Intent homeIntent=new Intent(MainActivity.this, first_time_show.class);
            startActivity(homeIntent);
            finish();
        }




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            //Intent homeIntent=new Intent(MainActivity.this, first_time_show.class);
            //startActivity(homeIntent);
            finish();
            }
        },sto);
    }
}