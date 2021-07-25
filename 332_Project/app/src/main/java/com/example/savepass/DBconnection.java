package com.example.savepass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//import android.widget.Toast;

//import androidx.annotation.Nullable;

public class DBconnection extends SQLiteOpenHelper {
    private static final String TAG = "DBcon";


    /*
    *Database Name-----------
     */
    private static final String DBNAME = "Safepass";


    private static final String LOGIN_TABLE = "Login";
    private static final String Email = "L_Email";
    private static final String Username = "L_Username";
    private static final String MPass = "L_password";


    private static final String PASS_TABLE = "Password";
    private static final String PTitle = "ptitle";
    private static final String PURL = "purl";
    private static final String PUSERNAME = "pusername";
    private static final String PPASS = "ppass";

    private static final String NOTE_TABLE = "Notes";
    private static final String NTITLE = "ntitle";
    private static final String NNOTES = "nnotes";

    private static final String ADDRESS_TABLE = "Address";
    private static final String ATITLE = "atitle";
    private static final String ANAME = "aname";
    private static final String AMOBILE = "amobile";
    private static final String AEMAIL = "aemail";
    private static final String AADD1 = "aadd1";
    private static final String AADD2 = "aadd2";


    public DBconnection(Context context) {
        super(context, DBNAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ct;
        ct = "CREATE TABLE "+LOGIN_TABLE+" ("+Email+" text primary key not null,"+Username+" text not null UNIQUE,L_password text not null)";
        db.execSQL(ct);
        String ct1;
        ct1 = "CREATE TABLE "+PASS_TABLE+" ("+PTitle+" text primary key not null,purl text not null,pusername text not null,ppass text not null)";
        db.execSQL(ct1);
        String ct2;
        ct2 = "CREATE TABLE "+NOTE_TABLE+" ("+NTITLE+" text primary key not null,nnotes text not null)";
        db.execSQL(ct2);

        String ct3;
        ct3 = "CREATE TABLE "+ADDRESS_TABLE+" ("+ATITLE+" text primary key not null,aname text not null,amobile text not null,aemail text not null,aadd1 text not null,aadd2 text not null)";
        db.execSQL(ct3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+PASS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+NOTE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ADDRESS_TABLE);

        onCreate(db);
    }


    /*
        * This are the insert methods for our project-------------
     */
    public boolean addData(String u,String e,String p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Username,u);
        cv.put(Email,e);
        cv.put(MPass,p);

        Log.d(TAG,"Adding: "+u+" and "+e+" and "+p+" to"+LOGIN_TABLE);

        long inserted = db.insert(LOGIN_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addpass(String t,String u,String n,String p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PTitle,t);
        cv.put(PURL,u);
        cv.put(PUSERNAME,n);
        cv.put(PPASS,p);

        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(PASS_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addnote(String t,String n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NTITLE,t);
        cv.put(NNOTES,n);


        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(NOTE_TABLE,null,cv);
        //db.close();
        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addaddress(String t,String n,String m,String e,String add1,String add2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ATITLE,t);
        cv.put(ANAME,n);
        cv.put(AMOBILE,m);
        cv.put(AEMAIL,e);
        cv.put(AADD1,add1);
        cv.put(AADD2,add2);

        //Log.d(TAG,"Adding: "+t+" and "+u+"  to"+LOGIN_TABLE);

        long inserted = db.insert(ADDRESS_TABLE,null,cv);
        //db.close();

        if(inserted!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    // *********************************************************==============================************************************************* //

    /*
     * This will be out select methods.
     */

    public boolean isLogin(String e,String p){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Login Where L_Email = \""+e+"\" or L_Username = \""+e+"\"";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst()){
            do{
                String pa;
                //em = data.getString(0);
                pa = data.getString(2);
                //e.equals(em) &&
                if(p.equals(pa)){
                    return true;
                }

            }while(data.moveToNext());
            return false;
        }
        else{
            return false;
        }
    }
    public Cursor getDBPass(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PASS_TABLE;
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getDBNote(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + NOTE_TABLE;
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }


    public boolean isValueExist(String value){
        String query = "SELECT * FROM Login WHERE L_Username = \""+value+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(query, null);
        //db.close();
        if(data.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }


    public Cursor getDBAdd(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ADDRESS_TABLE;
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }

    public Cursor getPass(String n){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PASS_TABLE +" Where ptitle = \""+n+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getNote(String n){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + NOTE_TABLE +" Where ntitle = \"" + n + "\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }
    public Cursor getAdd(String n){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ADDRESS_TABLE +" Where atitle = \""+n+"\"";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        return data;
    }

    public boolean isLog(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Login";
        Cursor data = db.rawQuery(query, null);
        //db.close();
        if(data.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }




}
