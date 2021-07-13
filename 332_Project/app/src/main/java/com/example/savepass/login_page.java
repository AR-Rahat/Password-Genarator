package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    Button l_done;
    EditText lmail,lpass;
    DatabaseConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        l_done=(Button) findViewById(R.id.login_done);
        lmail=findViewById(R.id.editTextTextEmailAddress);
        lpass=findViewById(R.id.editTextTextPassword);

        db=new DatabaseConnection(this);

        l_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = lmail.getText().toString();
                String pass = lpass.getText().toString();
                Boolean cd = db.create_login(mail, pass);
                if (cd == true)
                {
                    Toast.makeText(login_page.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(login_page.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();

                Intent l=new Intent(login_page.this, Setup_done.class);
                startActivity(l);
            }
        });
    }
}