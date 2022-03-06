package com.agplay.tv.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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

public class TrailerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        String idvid = getIntent().getExtras().getString("idvid");

        final WebView webView = (WebView) findViewById(R.id.webview);
        Button backMenu = (Button) findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Resources resources;
        resources = getResources();
        final String url = resources.getString(R.string.linksite)+"/api.php?action=view&idvid="+idvid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>>>>>>"+response);

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            webView.setWebChromeClient(new WebChromeClient());
                            webView.setWebViewClient(new WebViewClient());
                            String urlweb = obj.get("trailer").toString().replace("youtube.com/watch?v=","youtube.com/embed/");
                            webView.loadUrl(urlweb);
                            WebSettings webSettings = webView.getSettings();
                            //Habilitando o JavaScript
                            webSettings.setJavaScriptEnabled(true);


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
}
