package com.agplay.tv.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.agplay.tv.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Button register = (Button) findViewById(R.id.registerbtn);
        Button backMenu = (Button) findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class );
                startActivity(i);
                finish();
            }
        });

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2400);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);

        ImageView loading = (ImageView) findViewById(R.id.loadingimg);
        loading.startAnimation(rotate);

        //
        Button connectacc = (Button) findViewById(R.id.connectacc);
        connectacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mail = (EditText) findViewById(R.id.emailinputlogin);
                EditText pass = (EditText) findViewById(R.id.passwordinputlogin);

                //GET
                String email = mail.getText().toString();
                String passwd= pass.getText().toString();
                if(email.contains("@")){
                    if(passwd.trim().equals("") == false){
                        final LinearLayout loadingscreen = findViewById(R.id.loadingscreen);
                        loadingscreen.setVisibility(View.VISIBLE);

                        Resources resources;
                        resources = getResources();
                        String url = resources.getString(R.string.linksite)+"/api.php?action=log&mail=" + email + "&pass=" + passwd + "&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("strrrrr", ">>>>>>>"+response);

                                        JSONObject obj = null;
                                        try {
                                            obj = new JSONObject(response);

                                            loadingscreen.setVisibility(View.INVISIBLE);
                                            if(obj.getInt("cod") == 1){
                                                SharedPreferences.Editor editor = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE).edit();
                                                editor.putString("idusuario", obj.get("idConta").toString());
                                                editor.putString("user", obj.get("user").toString());
                                                editor.putString("email", obj.get("email").toString());
                                                editor.putString("pass", obj.get("pass").toString());

                                                editor.commit();

                                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                                startActivity(i);
                                                finish();
                                            }



                                            Toast.makeText(getApplicationContext(), obj.get("msg").toString(), Toast.LENGTH_LONG).show();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //displaying the error in toast if occurrs
                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                        //creating a request queue
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);

                    }else{
                        Toast.makeText(getApplicationContext(), "Senha Invalida", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Email Invalido", Toast.LENGTH_LONG).show();
                }
            }
        });

        //


    }
    public static final String LOGIN_AUTOMATICO = "INFORMACOES_LOGIN_AUTOMATICO";
}
