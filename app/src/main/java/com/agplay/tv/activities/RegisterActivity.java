package com.agplay.tv.activities;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button backMenu = (Button) findViewById(R.id.backMenu);

        Button register = (Button) findViewById(R.id.registerbtn);


        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LogActivity.class );
                startActivity(i);
                finish();
            }
        });

//Cadastro Campos
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2400);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);

        ImageView loading = (ImageView) findViewById(R.id.loadingimg);
        loading.startAnimation(rotate);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input          = (EditText) findViewById(R.id.inputail);
                EditText user      = (EditText) findViewById(R.id.userinput);
                EditText passwo     = (EditText) findViewById(R.id.passwordinput);

                String email        = input.getText().toString();
                String username        = user.getText().toString();
                String password        = passwo.getText().toString();

                if(email.contains("@")){
                    if(username.trim().equals("") == false) {
                        if(password.trim().equals("") == false) {

                            final LinearLayout loadingscreen = findViewById(R.id.loadingscreen);

                            loadingscreen.setVisibility(View.VISIBLE);

                            Resources resources;
                            resources = getResources();
                            String url = resources.getString(R.string.linksite)+"/api.php?action=cadastro&login=" + username + "&mail=" + email + "&pass=" + password + "&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";

                            ////////
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {


                                            JSONObject obj = null;
                                            try {
                                                obj = new JSONObject(response);
                                                Log.d("strrrrr", ">>" + obj.get("msg").toString());
                                                loadingscreen.setVisibility(View.INVISIBLE);
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
                            //adding the string request to request queue


                            /////////
                        }else{
                            Toast.makeText(getApplicationContext(), "Senha Invalido", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Nome de usuario Invalido", Toast.LENGTH_LONG).show();
                    }
             }else{
                    Toast.makeText(getApplicationContext(), "Email Invalido", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
