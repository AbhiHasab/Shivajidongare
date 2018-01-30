package com.abhijeethasabe.shivajidongare;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spanned;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import at.markushi.ui.CircleButton;

/**
 * Created by ADMIN on 11-02-2017.
 */
public class scanListActivity extends ActionBarActivity {

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "Category";

    JSONArray peoples = null;
    HashMap<String, Spanned> persons;
    ArrayList personList;
    TextView mycontact;
    ListView list;
    CircleButton facebook,youutube, linked;
    String url="https://www.youtube.com/channel/UCyvSHiA97ePuzlaXJ7t0p4A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persoanl);
        facebook=(CircleButton)findViewById(R.id.facebook);
        youutube=(CircleButton)findViewById(R.id.youtube);
        linked=(CircleButton)findViewById(R.id.linkedin);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = getOpenFacebookIntent(getApplicationContext());
                startActivity(facebookIntent);
            }
        });
        youutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoId = "GLxDglBfVyY";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
                intent.putExtra("VIDEO_ID", videoId);
                startActivity(intent);

            }
        });
        linked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

    }

    private Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/abhihasabe")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/abhihasabe")); //catches and opens a url to the desired page
        }
    }

    private void launchMarket() {
        Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Dheeraj+Purohit");
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Intent my =   new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/developer?id=Dheeraj+Purohit"));
            startActivity(my);
        }
    }
}
