package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_pass extends AppCompatActivity {

    //    private static final String TAG = "add_pass";
    private Toolbar toolbar;
    DBconnection DB;
    private EditText title, url, username, pass, text;
    private Button save, copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass);

        toolbar = (Toolbar) findViewById(R.id.addpass_bar);
        setSupportActionBar(toolbar);

        save = (Button) findViewById(R.id.save_pass1);
        title = (EditText) findViewById(R.id.item_name);
        url = (EditText) findViewById(R.id.pass_url);
        username = (EditText) findViewById(R.id.editTextTextPersonName5);
        pass = (EditText) findViewById(R.id.addPassword);

        copy = (Button) findViewById(R.id.copy_btn);
        text = (EditText) findViewById(R.id.addPassword);

        DB = new DBconnection(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ptitle, purl, pusername, ppass;
                ptitle = title.getText().toString();
                purl = url.getText().toString();
                pusername = username.getText().toString();
                ppass = pass.getText().toString();


                if (title.length() != 0 && url.length() != 0 && username.length() != 0 && pass.length() != 0) {
                    AddPass(ptitle, purl, pusername, ppass);
                    title.setText("");
                    url.setText("");
                    username.setText("");
                    pass.setText("");
                } else {
                    toastMessage("Empty fields aren't allowed!");
                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t = text.getText().toString();

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (!t.equals("")) {
                    ClipData clip = ClipData.newPlainText("edit", t);
                    clipboardManager.setPrimaryClip(clip);
                    toastMessage("Password Copied");
                } else {
                    toastMessage("Password Field is Empty");
                }
            }
        });
    }

    public void AddPass(String t, String u, String un, String p) {
        boolean insertData = DB.addpass(t, u, un, p);

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