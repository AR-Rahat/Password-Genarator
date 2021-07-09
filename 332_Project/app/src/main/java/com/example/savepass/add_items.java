package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class add_items extends AppCompatActivity {
    Button p_btn,n_btn,a_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        p_btn=(Button) findViewById(R.id.button);
        n_btn=(Button) findViewById(R.id.button2);
        a_btn=(Button) findViewById(R.id.button3);

        p_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(add_items.this, add_pass.class);
                startActivity(l);
            }
        });

        n_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(add_items.this, add_notes.class);
                startActivity(l);
            }
        });
        a_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(add_items.this, add_address.class);
                startActivity(l);
            }
        });
    }
}