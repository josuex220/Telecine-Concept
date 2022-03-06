package com.agplay.tv.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.agplay.tv.R;

import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class MyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        TextView usernameTexts = (TextView) findViewById(R.id.usernameTexts);
        TextView emailtext = (TextView) findViewById(R.id.emailtext);
        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        String user= prefs.getString("user", null);

        usernameTexts.setText(user);
        emailtext.setText(email);

    }
}
