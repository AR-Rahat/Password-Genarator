package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class singup_page extends AppCompatActivity {
    Button s_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_page);

        s_done=(Button) findViewById(R.id.signup_done);

        s_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(singup_page.this, login_page.class);
                startActivity(l);
            }
        });
    }
}