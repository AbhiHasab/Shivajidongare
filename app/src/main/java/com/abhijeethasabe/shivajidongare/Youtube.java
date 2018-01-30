package com.abhijeethasabe.shivajidongare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by ADMIN on 09-02-2017.
 */
public class Youtube extends ActionBarActivity {

    Button facebook,youutube;
    private WebView wv1;
    //String url="https://www.facebook.com/search/top/?q=shivaji%20dongre%20urf%20papu%20dongre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        wv1=(WebView)findViewById(R.id.webview);
        wv1.getSettings().setJavaScriptEnabled(true);
        String url="https://www.youtube.com/results?q=shivaji+dongre";
        Log.e("Url1",url);
        wv1.loadUrl(url);
    }
}
