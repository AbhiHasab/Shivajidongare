package com.abhijeethasabe.shivajidongare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    String[] gridViewString={
            "जीवनपट","आजचे कार्यकम","केलेली कामे","वचननामा","संपर्क","सामाजिक मीडिया",
    };
    int[] gridViewImageId={
            R.drawable.profile,R.drawable.facebook,R.drawable.work,R.drawable.futerwork,R.drawable.contact,
            R.drawable.socailmedia,
    };

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.three,R.drawable.two,R.drawable.nine,R.drawable.four,R.drawable.eight,R.drawable.seven,R.drawable.five,R.drawable.one};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // actionBar.setTitle(Html.fromHtml("<font color='#ff0000'>ActionBarTitle </font>"));
        init();
        CustomGridViewActivity customGridViewActivity=new CustomGridViewActivity(MainActivity.this,gridViewString,gridViewImageId);

        gridView=(GridView)findViewById(R.id.gridView);
        gridView.setAdapter(customGridViewActivity);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("hite",""+i);
                if(i==0)
                {
                    Intent i1=new Intent(getApplicationContext(),Profile.class);
                    startActivity(i1);
                }
                if (i==1)
                {
                    Intent i2=new Intent(getApplicationContext(),todaysfunction.class);
                    startActivity(i2);
                }
                if (i==2)
                {
                    Intent i3=new Intent(getApplicationContext(),kalalikame.class);
                    startActivity(i3);
                }
                if(i==3)
                {
                    Intent i4=new Intent(getApplicationContext(),vachannama.class);
                    startActivity(i4);
                }
                if(i==4)
                {
                    Intent i5=new Intent(getApplicationContext(),contact.class);
                    startActivity(i5);
                }
                if(i==5)
                {
                    Intent i6=new Intent(getApplicationContext(),socailmedia.class);
                    startActivity(i6);
                }
            }
        });
        TextView mycontact=(TextView)findViewById(R.id.mycontact);
        mycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7263942833"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
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
    private void init() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 4000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_refresh){
            Toast.makeText(MainActivity.this, "Refresh App", Toast.LENGTH_LONG).show();
        }*/
        if(id == R.id.action_new){
            Intent starscanListActivity=new Intent(MainActivity.this, scanListActivity.class);
            startActivity(starscanListActivity);
            /*Snackbar.make(id, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }

        return super.onOptionsItemSelected(item);
    }
}