package com.abhijeethasabe.shivajidongare;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import at.markushi.ui.CircleButton;

/**
 * Created by ADMIN on 09-02-2017.
 */
public class socailmedia extends ActionBarActivity {

    CircleButton facebook,youutube;
    String url="https://www.youtube.com/channel/UCyvSHiA97ePuzlaXJ7t0p4A";
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socailmedia);
        facebook=(CircleButton)findViewById(R.id.facebook);
        youutube=(CircleButton)findViewById(R.id.youtube);

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

        TextView mycontact=(TextView)findViewById(R.id.mycontact);
        mycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7263942833"));

                if (ActivityCompat.checkSelfPermission(socailmedia.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
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


    public static Intent getOpenFacebookIntent(Context context) {

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
}
