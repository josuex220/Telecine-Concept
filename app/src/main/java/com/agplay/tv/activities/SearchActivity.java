package com.agplay.tv.activities;

import android.content.Context;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class SearchActivity extends AppCompatActivity {

    LoginActivity.MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<String> id_movie = new ArrayList<>();
    ArrayList<String> tipo_movie = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button backMenu = (Button) findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final EditText inputsearch = (EditText) findViewById(R.id.inputsearch);
        inputsearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {

                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        progressBar.setVisibility(View.VISIBLE);
                        String input = inputsearch.getText().toString();
                        Resources resources;
                        resources = getResources();
                        String idusuario = input;
                        final String url = resources.getString(R.string.linksite) + "/api.php?action=search&s="+idusuario;
                        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        Log.d("String>>", id_movie.toString());
                                        try {
                                            id_movie.clear();
                                            animalNames.clear();
                                            tipo_movie.clear();

                                            for (int i = 0; i <= response.length(); i++) {
                                                JSONObject result = response.getJSONObject(i);

                                                animalNames.add(result.get("footer").toString().replace("original","w300_and_h450_bestv2"));
                                                id_movie.add(result.get("id").toString());
                                                tipo_movie.add(result.get("tipo").toString());



                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        progressBar.setVisibility(View.GONE);
                                        RecyclerView recyclerView = findViewById(R.id.recyclervid);
                                        LinearLayoutManager layoutManager
                                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                        AutoFitGridLayoutManager layoutMa = new AutoFitGridLayoutManager(getApplicationContext(), converteDpParaPx(getApplicationContext(),150));
                                        recyclerView.setLayoutManager(layoutMa);

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
                        return true;
                    }

                }
                return false;
            }
        });

        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        String email= prefs.getString("email", null);
        String pass= prefs.getString("pass", null);
        String user= prefs.getString("user", null);


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
