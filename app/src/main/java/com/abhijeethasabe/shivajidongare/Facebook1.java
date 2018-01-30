package com.abhijeethasabe.shivajidongare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by ADMIN on 11-02-2017.
 */
public class Facebook1 extends ActionBarActivity {

    Button facebook,youutube;
    private WebView wv1;
    String url="https://www.facebook.com/abhihasabe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook1);
        wv1=(WebView)findViewById(R.id.webview);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl("https://www.facebook.com/abhihasabe");
    }
}
