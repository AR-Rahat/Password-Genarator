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
    DBconnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db = new DBconnection(this);

        l_done=(Button) findViewById(R.id.login_done);
        lmail=findViewById(R.id.editTextTextEmailAddress);
        lpass=findViewById(R.id.editTextTextPassword);

        //db=new DatabaseConnection(this);

        l_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em,pa;
                em = lmail.getText().toString();
                pa =lpass.getText().toString();
                //toastMessage(em + pa);
                boolean in = db.isLogin(em,pa);
                //toastMessage(in == true?"true":"false");
                if(in){
                    toastMessage("Login Successful...");
                    Intent l=new Intent(login_page.this, Setup_done.class);
                    startActivity(l);
                }
                else{
                    toastMessage("Email or Password doesn't match");
                }

            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}