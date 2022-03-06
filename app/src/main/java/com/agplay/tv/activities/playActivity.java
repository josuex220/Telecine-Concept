package com.agplay.tv.activities;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.agplay.tv.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import org.json.JSONException;
import org.json.JSONObject;

import static com.agplay.tv.activities.LogActivity.LOGIN_AUTOMATICO;


public class playActivity extends AppCompatActivity {
    SimpleExoPlayer exoplayer;
    SimpleExoPlayerView exoPlayerView;
    String videourl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        SharedPreferences prefs = getSharedPreferences(LOGIN_AUTOMATICO, MODE_PRIVATE);

        String idusuario= prefs.getString("idusuario", null);
        String save = "";
        if(idusuario != null){
            save = "&save=1&iduser="+idusuario;
        }
        String idVid = getIntent().getExtras().getString("idv");
        Resources resources;
        resources = getResources();
        String url = resources.getString(R.string.linksite)+"/api.php?action=epview&idv="+idVid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>>>>>>"+response);

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);

                            videourl = obj.get("link1").toString();
                            if(obj.getInt("serv1") == 2){
                                RelativeLayout exoplayervideo = (RelativeLayout) findViewById(R.id.exoplayervideo);
                                exoplayervideo.setVisibility(View.GONE);
                                WebView webview = (WebView) findViewById(R.id.webview);
                                WebSettings webSettings = webview.getSettings();
                                webSettings.setJavaScriptEnabled(true);
                                WebViewClient webViewClient = new WebViewClient();
                                webview.setWebViewClient(webViewClient);
                                webview.loadUrl(videourl);

                            }else {
                                RelativeLayout webviewRelative = (RelativeLayout) findViewById(R.id.webviewRelative);
                                webviewRelative.setVisibility(View.GONE);
                                initializexo();
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
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }






    public void initializexo(){
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.videoView);
        try{
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector   = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoplayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videouri = Uri.parse(videourl);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoplayer);
            exoplayer.prepare(mediaSource);
            exoplayer.setPlayWhenReady(true);

            exoPlayerView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                    if(keyCode != KeyEvent.KEYCODE_BUTTON_B && keyCode != KeyEvent.KEYCODE_BACK ) {
                        exoPlayerView.showController();

                        return true;
                    }else {

                        exoplayer.setPlayWhenReady(false);
                        exoplayer.stop();
                        exoplayer.seekTo(0);

                        return false;
                    }
                }
            });

        }catch (Exception e){
            Log.d("Error Exoplayer", e.toString());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            exoplayer.setPlayWhenReady(false);
            exoplayer.stop();
            finish();

            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_BUTTON_B){
            exoplayer.setPlayWhenReady(false);
            exoplayer.stop();
            finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}

