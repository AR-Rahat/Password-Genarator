package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_address extends AppCompatActivity {
    private Toolbar toolbar;

    private static final String TAG = "add_address";

    DBconnection DB;
    private EditText title, name, mobile, email, add1, add2;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        Toolbar toolbar = findViewById(R.id.add_address_bar);
        setSupportActionBar(toolbar);

        save = (Button) findViewById(R.id.save_address1);
        title = (EditText) findViewById(R.id.item_title);
        name = (EditText) findViewById(R.id.address_name);
        mobile = (EditText) findViewById(R.id.address_phone);
        email = (EditText) findViewById(R.id.address_email);
        add1 = (EditText) findViewById(R.id.address_preaddress);
        add2 = (EditText) findViewById(R.id.address_peraddress);
        DB = new DBconnection(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String atitle, aname, amobile, aemail, aadd1, aadd2;
                atitle = title.getText().toString();
                aname = name.getText().toString();
                amobile = mobile.getText().toString();
                aemail = email.getText().toString();
                aadd1 = add1.getText().toString();
                aadd2 = add2.getText().toString();


                if (title.length() != 0 && name.length() != 0 && mobile.length() != 0 && email.length() != 0 && add1.length() != 0 && add2.length() != 0) {
                    AddAddress(atitle, aname, amobile, aemail, aadd1, aadd2);
                    title.setText("");
                    name.setText("");
                    mobile.setText("");
                    email.setText("");
                    add1.setText("");
                    add2.setText("");
                } else {
                    toastMessage("Empty fields aren't allowed!");
                }
            }

        });
    }

    public void AddAddress(String t, String u, String un, String p, String a1, String a2) {
        boolean insertData = DB.addaddress(t, u, un, p, a1, a2);

        if (insertData) {
            toastMessage("Successful");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}