package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    Button l_done;
    EditText lmail,lpass;
    CheckBox rem;
    DBconnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db = new DBconnection(this);

        l_done=(Button) findViewById(R.id.login_done);
        lmail=findViewById(R.id.editTextTextEmailAddress);
        lpass=findViewById(R.id.editTextTextPassword);
        rem=findViewById(R.id.checkBox);
        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=preferences.getString("remember","");
        /*if (checkbox.equals("true"))
        {

        }
        else if(checkbox.equals("false"))
        {
            Intent l=new Intent(login_page.this, Setup_done.class);
            startActivity(l);
        }*/

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
        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked())
                {
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(login_page.this,"checked",Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(login_page.this,"unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}