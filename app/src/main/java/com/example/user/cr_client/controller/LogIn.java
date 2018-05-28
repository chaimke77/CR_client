package com.example.user.cr_client.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.backend.DB_manager;

public class LogIn extends AppCompatActivity implements View.OnClickListener  {

    SharedPreferences sharedpreferences;
    EditText user, password;
    Button login;
    TextView reg;
    CheckBox check;

    public static final String PREF = "login.data";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    private void findViews() {
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        check = (CheckBox) findViewById(R.id.checkBox);
        login = (Button) findViewById(R.id.btn_login);
        reg = (TextView) findViewById(R.id.link_signup);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        findViews();

        sharedpreferences = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(USER)) {
            user.setText(sharedpreferences.getString(USER, ""));
        }
        if (sharedpreferences.contains(PASSWORD)) {
            password.setText(sharedpreferences.getString(PASSWORD, ""));
        }
    }

    public void Save(View view) {
        String n = user.getText().toString();
        String e = password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER, n);
        editor.putString(PASSWORD, e);
        editor.commit();
    }

    public void clear(View view) {
        user.setText("");
        password.setText("");
    }

    @Override
    public void onClick(View v) {
        String name = (user.getText().toString());
        String pass = (password.getText().toString());
        sharedpreferences = getSharedPreferences(PREF,Context.MODE_PRIVATE);
        if (v == login){
        if (sharedpreferences.contains(USER)) {
            if (sharedpreferences.getString(USER, "").equals(name) && sharedpreferences.getString(PASSWORD, "").equals(pass)) {
                goMain();
            }
        }
            try {
                new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected void onPostExecute(Boolean flag) {
                        super.onPostExecute(flag);
                        if(flag) {
                            goMain();
                        }
                    }

                    @Override
                    protected Boolean doInBackground(Void... params) {
                        return DBManagerFactory.getManager().custumerExsits(getPass(), getName());
                    }
                }.execute();
            } catch (Exception e) {

            }
            Toast.makeText(getBaseContext(), "User is not exsist", Toast.LENGTH_SHORT).show();
        }
    }


    String getName()
    {
        String name = (user.getText().toString());
        return name;
    }
    String getPass()
    {
        String pass = (password.getText().toString());
        return pass;
    }
    void goMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

