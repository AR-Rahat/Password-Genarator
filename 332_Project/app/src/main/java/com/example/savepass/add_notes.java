package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_notes extends AppCompatActivity {
    private Toolbar toolbar;
    private static final String TAG = "add_notes";

    DBconnection DB;
    private EditText title, note;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        Toolbar toolbar = findViewById(R.id.addnote_bar);
        setSupportActionBar(toolbar);

        save = (Button) findViewById(R.id.button4);
        title = (EditText) findViewById(R.id.item_name);
        note = (EditText) findViewById(R.id.add_note);

        DB = new DBconnection(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ntitle, nnote;
                ntitle = title.getText().toString();
                nnote = note.getText().toString();

                if (title.length() > 50) {
                    toastMessage("Title can not be exceed 50 characters");
                } else {
                    if (title.length() != 0 && note.length() != 0) {
                        AddNote(ntitle, nnote);
                        title.setText("");
                        note.setText("");
                    } else {
                        toastMessage("Empty fields aren't allowed!");
                    }
                }
            }
        });
    }

    public void AddNote(String t, String u) {
        boolean insertData = DB.addnote(t, u);

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