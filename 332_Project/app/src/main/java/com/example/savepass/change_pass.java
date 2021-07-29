package com.example.savepass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class change_pass extends AppCompatActivity {
    Button cp;
    private Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText op,np,cnp;
    String un,mp,nmp,cnmp;
    DBconnection DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        Toolbar toolbar= findViewById(R.id.changepass_bar);
        setSupportActionBar(toolbar);

        DB = new DBconnection(this);
        op = (EditText) findViewById(R.id.oldpass);
        np = (EditText) findViewById(R.id.newmasterpass);
        cnp = (EditText) findViewById(R.id.conmaspass);

        sharedPreferences=getSharedPreferences("loginusername",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean savelogininfo = sharedPreferences.getBoolean("saveusername",true);
        if (savelogininfo==true)
        {
            un = sharedPreferences.getString("username",null);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cp=(Button) findViewById(R.id.change_pass);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = op.getText().toString();
                nmp = np.getText().toString();
                cnmp = cnp.getText().toString();
                boolean in = DB.isLogin(un,mp);
                if(in){
                    if(nmp.length()!=0 && cnmp.length()!=0) {
                        if(validatepassword(nmp)) {
                            if (nmp.equals(cnmp)) {

                                op.setError(null);
                                np.setError(null);

                                DB.ChangePass(un, nmp);
                                Intent i = new Intent(change_pass.this, login_page.class);
                                i.putExtra("id", 2);
                                logout();
                                startActivity(i);
                            } else {
                                np.setError("Passwords didn't match");
                                // New password mile nai....
                            }
                        }
                    }
                    else{
                        np.setError("Field can't be empty");
                        // oita faka tai........
                    }
                }else {
                    op.setError("Old password doesn't match");
                    // Old password doesn't match....
                }
            }
        });
    }

    public boolean validatepassword(String p)
    {
//
        if (p.length()==0)
        {
            np.setError("Field can't be empty");
            return false;
        }
        else if(p.length()<8){
            np.setError("Password should be minimum 8 character long");
           np.setFocusable(true);
            np.setFocusableInTouchMode(true);
            np.requestFocus();
           np.setSelection(np.getText().length());
            return false;
        }
        else
        {
           np.setError(null);
            //suser.setErrorEnabled(false);
            return true;
        }
    }
    private void logout()
    {
        editor.putBoolean("saveusername",false);
        editor.putBoolean("email",false);
        editor.clear();
        editor.commit();
    }
}