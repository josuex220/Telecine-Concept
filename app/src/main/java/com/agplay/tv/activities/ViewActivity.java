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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.agplay.tv.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Button watchbtn = (Button) findViewById(R.id.watchbtn);

        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        final String idusuario= prefs.getString("idusuario", null);
        final String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        final String user= prefs.getString("user", null);


        Button backMenu = (Button) findViewById(R.id.back_menu);
        final LinearLayout loadingscreen = findViewById(R.id.loadingscreen);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2400);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        ImageView loading = (ImageView) findViewById(R.id.loadingimg);
        loading.startAnimation(rotate);

        final ImageView poster = (ImageView) findViewById(R.id.poster_view);
        final ImageView bg     = (ImageView) findViewById(R.id.bg_view);
        final TextView titulo  = (TextView) findViewById(R.id.title_view);
        final TextView titulo1 = (TextView) findViewById(R.id.title1_view);
        final TextView sinopse = (TextView) findViewById(R.id.sinopse_view);
        final TextView genero  = (TextView) findViewById(R.id.genero_view);
        final TextView duration= (TextView) findViewById(R.id.duration_view);
        final TextView produtora= (TextView) findViewById(R.id.produtora_view);
        final TextView qtdrating= (TextView) findViewById(R.id.qtdrating_view);
        final ImageView pg      = (ImageView) findViewById(R.id.pg);
        final TextView idmovie        = (TextView) findViewById(R.id.idmovie);


        watchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idmovie.getText().toString();
                Intent i = new Intent(getApplicationContext(),playingActivity.class);
                i.putExtra("idvid", id);
                startActivity(i);
            }
        });
        Button trailerbtn = (Button) findViewById(R.id.trailerbtn);

        trailerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idmovie.getText().toString();
                Intent intent = new Intent(getApplicationContext(), TrailerActivity.class);
                intent.putExtra("idvid", id);
                startActivity(intent);
            }
        });

        final Button mylistadd = (Button) findViewById(R.id.mylistadd);
        mylistadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idmovi = idmovie.getText().toString();
                Resources resources;
                resources = getResources();
                String url = resources.getString(R.string.linksite)+"/api.php?action=addmylist&idmb="+idusuario+"&idvid="+idmovi+"&&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("strrrrr", ">>>>>>>"+response);

                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(response);
                                    if(obj.get("cod").equals("1")){
                                        int imgResource = R.drawable.ic_remove;
                                        mylistadd.setCompoundDrawablesWithIntrinsicBounds(0, imgResource, 0, 0);
                                    }if(obj.get("cod").equals("2")){
                                        int imgResource = R.drawable.ic_mylist;
                                        mylistadd.setCompoundDrawablesWithIntrinsicBounds(0, imgResource, 0, 0);
                                    }
                                    Toast.makeText(getApplicationContext(), obj.get("msg").toString(),Toast.LENGTH_SHORT).show();


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
            }
        });

        Button errorinfo = (Button) findViewById(R.id.errorinfo);
        errorinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources resources;
                resources = getResources();
                String url = resources.getString(R.string.linksite)+"/api.php?action=errorvid&idvid="+idmovie.getText().toString()+"&&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("strrrrr", ">>>>>>>"+response);

                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(response);

                                    Toast.makeText(getApplicationContext(), "Obrigado por informar.",Toast.LENGTH_SHORT).show();


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
            }
        });
        String idVid = getIntent().getExtras().getString("idvid");
        Resources resources;
        resources = getResources();
        String url = resources.getString(R.string.linksite)+"/api.php?action=view&idvid="+idVid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>>>>>>"+response);

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            String id = obj.get("id").toString();
                            Picasso.get().load(obj.get("bg").toString().replace("original", "w1280")).into(bg);
                            Picasso.get().load(obj.get("footer").toString().replace("original", "w300_and_h450_bestv2")).into(poster);
                            titulo.setText(obj.get("titulo").toString()+" ("+obj.get("year").toString()+")");
                            titulo1.setText(obj.get("titulo").toString());
                            sinopse.setText(obj.get("sinopse").toString());
                            genero.setText(obj.get("genero").toString());
                            duration.setText(obj.get("duration").toString());
                            produtora.setText(obj.get("diretor").toString());

                            qtdrating.setText(obj.get("rating").toString());
                            idmovie.setText(id);
                            loadingscreen.setVisibility(View.GONE);
                            if(obj.get("pg").toString().equals("L")){
                                Picasso.get().load(R.drawable.classificacaolivre).into(pg);
                            }else if(obj.get("pg").toString().equals("10")){
                                Picasso.get().load(R.drawable.classificacao10).into(pg);
                            }else if(obj.get("pg").toString().equals("12")){
                                Picasso.get().load(R.drawable.classificacao12).into(pg);
                            }else if(obj.get("pg").toString().equals("14")){
                                Picasso.get().load(R.drawable.classificacao14).into(pg);
                            }else if(obj.get("pg").toString().equals("16")){
                                Picasso.get().load(R.drawable.classificacao16).into(pg);
                            }else if(obj.get("pg").toString().equals("18")){
                                Picasso.get().load(R.drawable.classificacao18).into(pg);
                            }



                            Resources resources1;
                            resources1 = getResources();
                            String url = resources1.getString(R.string.linksite)+"/api.php?action=listcheck&idmb="+idusuario+"&idvid="+id+"&&token=asdhiua))(s)(o_dgzxyhcu7t61273nhcxuzaiyt78213yuiaxsdyt677@_)(*";
                            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("strrrrr", ">>>>>>>"+response);

                                            JSONObject obj1 = null;
                                            try {
                                                obj1 = new JSONObject(response);
                                                if(obj1.get("result").toString().equals("1")){
                                                    int imgResource = R.drawable.ic_remove;
                                                    mylistadd.setCompoundDrawablesWithIntrinsicBounds(0, imgResource, 0, 0);
                                                }if(obj1.get("result").toString().equals("0")){
                                                    int imgResource = R.drawable.ic_mylist;
                                                    mylistadd.setCompoundDrawablesWithIntrinsicBounds(0, imgResource, 0, 0);
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
                                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            //creating a request queue
                            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                            stringRequest1.setRetryPolicy(new DefaultRetryPolicy(
                                    10000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            requestQueue1.add(stringRequest1);
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
}
