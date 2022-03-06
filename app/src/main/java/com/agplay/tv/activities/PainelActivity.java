package com.agplay.tv.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agplay.tv.R;

import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class PainelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel);

        TextView usernameText = (TextView) findViewById(R.id.usernameText);
        Button  btn_mydados   = (Button) findViewById(R.id.btn_mydados);
        Button  playlist      = (Button) findViewById(R.id.btn_mylist);
        Button  recovery      = (Button) findViewById(R.id.btn_recovery);
        Button  sair          = (Button) findViewById(R.id.btn_exitacc);

        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        String user= prefs.getString("user", null);

        Button backMenu = (Button) findViewById(R.id.backMenu);
        recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RecentActivity.class);
                startActivity(i);

            }
        });
        btn_mydados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyInfoActivity.class);
                startActivity(i);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlaylistActivity.class);
                startActivity(i);

            }
        });
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        usernameText.setText("Olá "+user);

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE).edit();
                editor.putString("idusuario", null);
                editor.putString("user", null);
                editor.putString("email", null);
                editor.putString("pass", null);

                editor.commit();
                Intent i = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
