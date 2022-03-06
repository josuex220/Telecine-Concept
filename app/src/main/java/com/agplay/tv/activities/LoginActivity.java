package com.agplay.tv.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.agplay.tv.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class LoginActivity extends AppCompatActivity  {
    private TextView mTextViewResult;
    private ImageView mPoster;
    private RequestQueue mQueue;
    private long backPressedTime = 0;


    //
    MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<String> id_movie = new ArrayList<>();
    ArrayList<String> tipo_movie = new ArrayList<>();

    MyRecyclerViewAdapter adapter1;
    ArrayList<String> animalNames1 = new ArrayList<>();
    ArrayList<String> id_movie1 = new ArrayList<>();
    ArrayList<String> tipo_movie1 = new ArrayList<>();

    ArrayList<String> animalNames2 = new ArrayList<>();
    ArrayList<String> id_movie2 = new ArrayList<>();
    ArrayList<String> tipo_movie2 = new ArrayList<>();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2400);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        ImageView loading = (ImageView) findViewById(R.id.loadingimg);
        loading.startAnimation(rotate);
        Button registerbtn= (Button) findViewById(R.id.registerbtn);
        Button mypainel= (Button) findViewById(R.id.mypainel);
        Button draw = (Button) findViewById(R.id.menu_expand);
        Button sign_in= (Button) findViewById(R.id.sign_in);
        Button search_btn =(Button) findViewById(R.id.search_btn);


        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        String user= prefs.getString("user", null);

        Button btnlancamento = (Button) findViewById(R.id.btnlancamento);
        Button btnacao       = (Button) findViewById(R.id.btnacao);
        Button animabtn       = (Button) findViewById(R.id.animabtn);
        Button aventurabtn       = (Button) findViewById(R.id.aventurabtn);
        Button combtn       = (Button) findViewById(R.id.combtn);
        Button docbtn       = (Button) findViewById(R.id.docbtn);
        Button dramabtn       = (Button) findViewById(R.id.dramabtn);
        Button familiabtn       = (Button) findViewById(R.id.familiabtn);
        Button faroestebtn       = (Button) findViewById(R.id.faroestebtn);
        Button fcienbtn       = (Button) findViewById(R.id.fcienbtn);
        Button guerrabtn       = (Button) findViewById(R.id.guerrabtn);
        Button historiabtn       = (Button) findViewById(R.id.historiabtn);
        Button misteriobtn       = (Button) findViewById(R.id.misteriobtn);
        Button romancebtn       = (Button) findViewById(R.id.romancebtn);
        Button terrorbtn       = (Button) findViewById(R.id.terrorbtn);


        btnlancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Lançamentos");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        btnacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Ação");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        animabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Animação");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        aventurabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Aventura");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        combtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Comédia");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Documentário");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        dramabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Drama");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        familiabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Família");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        faroestebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Faroeste");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        fcienbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Ficção");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        guerrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Guerra");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        historiabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "História");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        misteriobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Mistério");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        romancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Romance");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        terrorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Terror");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });








        if(user == null){
            mypainel.setVisibility(View.GONE);
            sign_in.setVisibility(View.VISIBLE);
        }else{
            mypainel.setVisibility(View.VISIBLE);
            sign_in.setVisibility(View.GONE);
        }

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LogActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        mypainel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PainelActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });



        //JSON






        jsonPARSELANCAMENTOS();


        ///



        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawey_layout);



        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }




    public static class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
        public static Context ctx;
        private List<String> mTipo;
        private List<String> mBack;
        private List<String> mId;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<String> bg, ArrayList<String> id_movie, List<String> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mTipo = data;
            this.mBack = bg;
            this.mId   = id_movie;
            this.ctx   = context;

        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.card_play, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String animal = mBack.get(position);
            String idmovie= mId.get(position);
            String mtipo  = mTipo.get(position);


            holder.myid.setText(idmovie);
            holder.mytipo.setText(mtipo);
            Picasso.get().load(animal).into(holder.myTextView);
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mTipo.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView myTextView;
            TextView myid;
            TextView mytipo;
            Button play;


            ViewHolder(View itemView) {
                super(itemView);

                myid = itemView.findViewById(R.id.movie_id);
                mytipo=itemView.findViewById(R.id.movie_tipo);
                myTextView = itemView.findViewById(R.id.bg_poster_home);
                play = itemView.findViewById(R.id.play_mv);



                itemView.setOnClickListener(this);
                play.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {


                if(mytipo.getText().toString().equals("1")){
                    Intent i = new Intent(ctx, ViewActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("idvid", myid.getText().toString());
                    ctx.startActivity(i);
                }else if(mytipo.getText().toString().equals("2")){
                    Intent i = new Intent(ctx, ViewSeriesActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("idvid", myid.getText().toString());
                    ctx.startActivity(i);
                }



            }


        }



        // convenience method for getting data at click position
        String getItem(int id) {
            return mTipo.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }

    }



    public void jsonPARSELANCAMENTOS(){
        Resources resources;
        resources = getResources();
        final String url = resources.getString(R.string.linksite)+"/api.php?action=last";
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {


                            for(int i = 0; i <= response.length(); i++) {
                                JSONObject result = response.getJSONObject(i);

                                animalNames.add(result.get("footer").toString().replace("original","w300_and_h450_bestv2"));
                                id_movie.add(result.get("id").toString());
                                tipo_movie.add(result.get("tipo").toString());



                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager);


                        adapter = new MyRecyclerViewAdapter(getApplicationContext(), animalNames, id_movie, tipo_movie);

                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);





        final String url1 = resources.getString(R.string.linksite)+"/api.php?action=lastmovie";
        JsonArrayRequest stringRequest1 = new JsonArrayRequest(Request.Method.GET, url1,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {


                            for(int i = 0; i <= response.length(); i++) {
                                JSONObject result1 = response.getJSONObject(i);

                                animalNames1.add(result1.get("footer").toString().replace("original","w300_and_h450_bestv2"));
                                id_movie1.add(result1.get("id").toString());
                                tipo_movie1.add(result1.get("tipo").toString());


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView recyclerView = findViewById(R.id.cardfilm);
                        LinearLayoutManager layoutManager1
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager1);


                        adapter1 = new MyRecyclerViewAdapter(getApplicationContext(), animalNames1, id_movie1, tipo_movie1);

                        recyclerView.setAdapter(adapter1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                });
        stringRequest1.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);

        final String url2 = resources.getString(R.string.linksite)+"/api.php?action=lastseries";
        JsonArrayRequest stringRequest2 = new JsonArrayRequest(Request.Method.GET, url2,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {


                            for(int i = 0; i <= response.length(); i++) {
                                JSONObject result1 = response.getJSONObject(i);

                                animalNames2.add(result1.get("footer").toString().replace("original","w300_and_h450_bestv2"));
                                id_movie2.add(result1.get("id").toString());
                                tipo_movie2.add(result1.get("tipo").toString());


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView recyclerView = findViewById(R.id.cardserie);
                        LinearLayoutManager layoutManager2
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager2);


                        adapter1 = new MyRecyclerViewAdapter(getApplicationContext(), animalNames2, id_movie2, tipo_movie2);

                        recyclerView.setAdapter(adapter1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                });
        stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        requestQueue2.add(stringRequest2);




        final String url3 = resources.getString(R.string.linksite)+"/api.php?action=config";
        JsonObjectRequest stringRequest3 = new JsonObjectRequest(Request.Method.GET, url3,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject result1) {

                        try {


                            ImageView bglinearlayout = (ImageView) findViewById(R.id.imageView222);
                            Picasso.get().load(result1.get("bg").toString()).into(bglinearlayout);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        LinearLayout loadingscreen = findViewById(R.id.loadingscreen);
                                        loadingscreen.setVisibility(View.GONE);
                                    }
                                },
                                2000);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                });
        stringRequest3.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue3 = Volley.newRequestQueue(getApplicationContext());
        requestQueue3.add(stringRequest3);

    }

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Pressione novamente para sair do app",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            super.onBackPressed();       // bye
        }
    }


}
