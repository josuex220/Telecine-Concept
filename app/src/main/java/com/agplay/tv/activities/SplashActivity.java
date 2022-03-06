package com.agplay.tv.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
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

import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2400);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);


        ImageView loading = (ImageView) findViewById(R.id.loading);
        loading.startAnimation(rotate);
        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        final String email= prefs.getString("email", null);
        final String pass= prefs.getString("pass", null);
        final String user= prefs.getString("user", null);
        if (idusuario == null) {
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(getApplicationContext(), LogActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }else{
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Resources resources;
                    resources = getResources();
                    String url = resources.getString(R.string.linksite)+"/api.php?action=log&mail=" + email + "&pass=" + pass + "&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("strrrrr", ">>>>>>>"+response);

                                    JSONObject obj = null;
                                    try {
                                        obj = new JSONObject(response);

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
                                        }else if(obj.getInt("cod") == 0){
                                            SharedPreferences.Editor editor = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE).edit();
                                            editor.putString("idusuario", null);
                                            editor.putString("user", null);
                                            editor.putString("email",null);
                                            editor.putString("pass", null);

                                            editor.commit();

                                            Intent i = new Intent(getApplicationContext(), LogActivity.class);
                                            startActivity(i);
                                            finish();
                                        }




                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //displaying the error in toast if occurrs
                                    Toast.makeText(getApplicationContext(), "Houve um problema de conexao. por favor, verifique e tente novamente", Toast.LENGTH_LONG).show();
                                }
                            });

                    //creating a request queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                }
            }, 3000);
        }
    }

}

