package com.agplay.tv.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
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

public class ViewSeriesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<String> id_movie = new ArrayList<>();
    ArrayList<String> tipo_movie = new ArrayList<>();
    ArrayList<String> title_ep = new ArrayList<>();
    String idfilm ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_series);

        final ArrayList<String> classes = new ArrayList<>();


        final Spinner spinner = findViewById(R.id.spinnerTemp);
        final ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,R.layout.spinner_item, classes);
        spinner.setOnItemSelectedListener(this);

        Button back_menu = (Button) findViewById(R.id.back_menu);
        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button watchbtn = (Button) findViewById(R.id.watchbtn);

        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        final String idusuario= prefs.getString("idusuario", null);
        final String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        final String user= prefs.getString("user", null);


        Button backMenu = (Button) findViewById(R.id.back_menu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


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
                LinearLayout eps = (LinearLayout) findViewById(R.id.eps);
                LinearLayout episodios= (LinearLayout) findViewById(R.id.episodios);
                episodios.setVisibility(View.GONE);
                eps.setVisibility(View.VISIBLE);
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
                            String tmdb= obj.get("tmdb").toString();
                            qtdrating.setText(obj.get("rating").toString());
                            idmovie.setText(id);
                            idfilm = obj.get("tmdb").toString();
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
                            Resources resources11;
                            resources11 = getResources();

                            String idVid1= tmdb;
                            String url11 = resources11.getString(R.string.linksite)+"/api.php?action=temp&idf="+idVid1;
                            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url11,null,
                                    new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {

                                            try {


                                                for(int i = 0; i <= response.length(); i++) {
                                                    JSONObject result = response.getJSONObject(i);
                                                    classes.add(result.get("temp").toString()+"ª Temporada");
                                                }



                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            Log.d("Class", classes.toString());
                                            spinner.setAdapter(adapterSpinner);


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
        requestQueue.add(stringRequest);



    }

    public static class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
        public static Context ctx;
        private List<String> mTipo;
        private List<String> mBack;
        private List<String> mTitle;
        private List<String> mId;

        private LayoutInflater mInflater;
        private MyRecyclerViewAdapter.ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<String> bg, ArrayList<String> id_movie, List<String> data, ArrayList<String> title) {
            this.mInflater = LayoutInflater.from(context);
            this.mTipo = data;
            this.mBack = bg;
            this.mId   = id_movie;
            this.ctx   = context;
            this.mTitle= title;

        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.card_ep, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
            String animal = mBack.get(position);
            String idmovie= mId.get(position);
            String info  = mTipo.get(position);
            String title = mTitle.get(position);

            holder.mytitle.setText(title);
            holder.myid.setText(idmovie);
            holder.mytipo.setText(info);

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
            TextView mytitle;
            Button play;


            ViewHolder(View itemView) {
                super(itemView);

                myid = itemView.findViewById(R.id.id_ep);
                myTextView = itemView.findViewById(R.id.imgbg);
                mytipo = itemView.findViewById(R.id.ep_idinfo);
                play = itemView.findViewById(R.id.btn_viewep);
                mytitle = itemView.findViewById(R.id.title_ep);



                itemView.setOnClickListener(this);
                play.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, playActivity.class);
                i.putExtra("idv", myid.getText().toString());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);
                    play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(ctx, playActivity.class);
                            i.putExtra("idv", myid.getText().toString());
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ctx.startActivity(i);
                        }
                    });

            }


        }



        // convenience method for getting data at click position
        String getItem(int id) {
            return mTipo.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(MyRecyclerViewAdapter.ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        String tempSelect = text.replaceAll("[^0-9]", "");

        Resources resources;
        resources = getResources();
        final String url = resources.getString(R.string.linksite)+"/api.php?action=epall&idf="+idfilm+"&temp="+tempSelect;
        Log.d("URL", url);
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            animalNames.clear();
                            id_movie.clear();
                            tipo_movie.clear();
                            title_ep.clear();
                            for(int i = 0; i <= response.length(); i++) {
                                JSONObject result = response.getJSONObject(i);
                                Log.d("EPS", result.toString());
                                String ep_idinfo = "EP"+result.get("ep").toString()+"    T"+result.get("temp");


                                animalNames.add(result.get("bg").toString().replace("original","w1280"));
                                id_movie.add(result.get("id").toString());
                                tipo_movie.add(ep_idinfo);
                                title_ep.add(result.get("titulo").toString());



                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView recyclerView = findViewById(R.id.recyclerep);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        AutoFitGridLayoutManager layoutMa = new AutoFitGridLayoutManager(getApplicationContext(), converteDpParaPx(getApplicationContext(),300));

                        recyclerView.setLayoutManager(layoutMa);


                        adapter = new MyRecyclerViewAdapter(getApplicationContext(), animalNames, id_movie, tipo_movie, title_ep);

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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public class AutoFitGridLayoutManager extends GridLayoutManager {

        private int columnWidth;
        private boolean columnWidthChanged = true;

        public AutoFitGridLayoutManager(Context context, int columnWidth) {
            super(context, 1);

            setColumnWidth(columnWidth);
        }


        public void setColumnWidth(int newColumnWidth) {
            if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
                columnWidth = newColumnWidth;
                columnWidthChanged = true;
            }
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            if (columnWidthChanged && columnWidth > 0) {
                int totalSpace;
                if (getOrientation() == VERTICAL) {
                    totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
                } else {
                    totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
                }
                int spanCount = Math.max(1, totalSpace / columnWidth);
                setSpanCount(spanCount);
                columnWidthChanged = false;
            }
            super.onLayoutChildren(recycler, state);
        }
    }
    public static int converteDpParaPx(Context context, int dps) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, displayMetrics));
    }
}
