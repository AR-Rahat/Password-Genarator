package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class viewnotes extends AppCompatActivity {

    DBconnection DB;
    private EditText title,note;
    private Button edit;
    private String nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotes);
        title = findViewById(R.id.item_name);
        note = findViewById(R.id.add_note);
        edit = findViewById(R.id.button4);
        DB = new DBconnection(this);

        Intent Ri = getIntent();
        nt = Ri.getStringExtra("title");

        cNote a = new cNote();
        Cursor dt = DB.getNote(nt);
        if(dt.moveToFirst()){
            do{
                a.setTitle(dt.getString(0));
                a.setNote(dt.getString(1));
            }while(dt.moveToNext());
            title.setText(a.getTitle());
            disableEditText(title);
            note.setText(a.getNote());
            disableEditText(note);

        }else {
            toastMessage("Sorry no data has been found");
        }
    }

    public void showPopup(View view)
    {
        PopupMenu popup=new PopupMenu(this,view);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu,popup.getMenu());
        popup.show();
        //getMenuInflater().inflate(R.menu.popup_menu,menu);
    }
    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        //editText.setBackgroundColor(Color.TRANSPARENT);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}