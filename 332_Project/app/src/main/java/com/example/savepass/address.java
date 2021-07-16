package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class address extends AppCompatActivity {

    DBconnection DB;
    private ListView lv_add1;
    private Cursor dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        lv_add1 = findViewById(R.id.lv_add);
        DB = new DBconnection(this);

        Cursor dt = DB.getDBAdd();
        if(dt.moveToFirst()){
            Fill(dt);
        }

        lv_add1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String I = String.valueOf(i);
                int L = I.length();
                L+=2;
                String title = name.substring(L);

                Intent editScreenIntent = new Intent(address.this, viewaddress.class);
                editScreenIntent.putExtra("title",title);
                startActivity(editScreenIntent);
            }
        });

    }
    public void Fill(Cursor dtb){

        ArrayList<String> listData = new ArrayList<>();
        Integer i = 1;
        do{
            //get the value from the database in column 1
            //then add it to the ArrayList
            String s = dtb.getString(0);
            String a = Integer.toString(i);
            String b = a+": "+s;
            //toastMessage(b);
            listData.add(b);
            i++;
        }while(dtb.moveToNext());
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv_add1.setAdapter(adapter);
        dtb.close();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}