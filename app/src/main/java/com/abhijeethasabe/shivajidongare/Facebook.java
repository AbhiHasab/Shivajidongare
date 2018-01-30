package com.abhijeethasabe.shivajidongare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by ADMIN on 09-02-2017.
 */
public class Facebook extends ActionBarActivity {

    Button facebook,youutube;
    private WebView wv1;
    String url="https://www.facebook.com/search/top/?q=shivaji%20dongre%20urf%20papu%20dongre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);
        wv1=(WebView)findViewById(R.id.webview);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl("https://www.facebook.com/shivajai.dongare?hc_ref=SEARCH");
    }
}
