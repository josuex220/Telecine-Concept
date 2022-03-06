package com.agplay.tv.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.agplay.tv.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;
import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;

public class LacamentoActivity extends AppCompatActivity {
    LoginActivity.MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<String> id_movie = new ArrayList<>();
    ArrayList<String> tipo_movie = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacamento);



        Button mypainel= (Button) findViewById(R.id.mypainel);
        Button draw = (Button) findViewById(R.id.menu_expand);
        Button sign_in= (Button) findViewById(R.id.sign_in);
        Button search_btn =(Button) findViewById(R.id.search_btn);
        ImageView bg_poster_home = (ImageView) findViewById(R.id.bg_poster_home);
        Button play_mv = (Button) findViewById(R.id.play_mv);


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
                startActivity(i);
            }
        });
        btnacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Ação");
                startActivity(i);
            }
        });
        animabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Animação");
                startActivity(i);
            }
        });
        aventurabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Aventura");
                startActivity(i);
            }
        });
        combtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Comédia");
                startActivity(i);
            }
        });
        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Documentário");
                startActivity(i);
            }
        });
        dramabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Drama");
                startActivity(i);
            }
        });
        familiabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Família");
                startActivity(i);
            }
        });
        faroestebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Faroeste");
                startActivity(i);
            }
        });
        fcienbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Ficção");
                startActivity(i);
            }
        });
        guerrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Guerra");
                startActivity(i);
            }
        });
        historiabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "História");
                startActivity(i);
            }
        });
        misteriobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Mistério");
                startActivity(i);
            }
        });
        romancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Romance");
                startActivity(i);
            }
        });
        terrorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LacamentoActivity.class);
                i.putExtra("category", "Terror");
                startActivity(i);
            }
        });


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




        // set up the RecyclerView







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
                startActivity(i);

            }
        });
        mypainel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PainelActivity.class);
                startActivity(i);

            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });
        play_mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });
        bg_poster_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });
        jsonPARSELANCAMENTOS();
    }

    public void jsonPARSELANCAMENTOS() {
        final ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        String category = getIntent().getExtras().getString("category");

        if(category.equals("Lançamentos")){
            TextView title = (TextView) findViewById(R.id.titletextview);
            title.setText(category);
            String gen = category;
            String categ = "lanc";
            Resources resources;
            resources = getResources();
            final String url = resources.getString(R.string.linksite) + "/api.php?action=lanc";
            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try {


                                for (int i = 0; i <= response.length(); i++) {
                                    JSONObject result = response.getJSONObject(i);

                                    animalNames.add(result.get("footer").toString().replace("original", "w300_and_h450_bestv2"));
                                    id_movie.add(result.get("id").toString());
                                    tipo_movie.add(result.get("tipo").toString());


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            RecyclerView recyclerView = findViewById(R.id.recyclervid);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setScrollingTouchSlop(0);

                            AutoFitGridLayoutManager layoutMa = new AutoFitGridLayoutManager(getApplicationContext(), converteDpParaPx(getApplicationContext(),150));

                            recyclerView.setLayoutManager(layoutMa);

                            progressBar2.setVisibility(View.GONE);
                            adapter = new LoginActivity.MyRecyclerViewAdapter(getApplicationContext(), animalNames, id_movie, tipo_movie);
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
        else if(category.equals("Lançamentos") == false){
            TextView title = (TextView) findViewById(R.id.titletextview);
            title.setText(category);
            String gen = category;
            String categ = "genero";
            Resources resources;
            resources = getResources();
            final String url = resources.getString(R.string.linksite) + "/api.php?action="+categ+"&genero="+gen;
            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try {


                                for (int i = 0; i <= response.length(); i++) {
                                    JSONObject result = response.getJSONObject(i);

                                    animalNames.add(result.get("footer").toString().replace("original", "w300_and_h450_bestv2"));
                                    id_movie.add(result.get("id").toString());
                                    tipo_movie.add(result.get("tipo").toString());


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            RecyclerView recyclerView = findViewById(R.id.recyclervid);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setScrollingTouchSlop(0);

                            AutoFitGridLayoutManager layoutMa = new AutoFitGridLayoutManager(getApplicationContext(), converteDpParaPx(getApplicationContext(),150));

                            recyclerView.setLayoutManager(layoutMa);

                            progressBar2.setVisibility(View.GONE);
                            adapter = new LoginActivity.MyRecyclerViewAdapter(getApplicationContext(), animalNames, id_movie, tipo_movie);
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
