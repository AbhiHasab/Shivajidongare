package com.abhijeethasabe.shivajidongare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ADMIN on 08-02-2017.
 */
    public class todaysfunction extends ActionBarActivity {

        String myJSON;

        private static final String TAG_RESULTS="result";
        private static final String TAG_ID = "Category";

        JSONArray peoples = null;
        HashMap<String, Spanned> persons;
        ArrayList personList;
        TextView mycontact;
        ListView list;
        private AdView mAdView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.todaysfunction);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList();

        getData();

        mycontact=(TextView) findViewById(R.id.mycontact);
        mycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7263942833"));

                if (ActivityCompat.checkSelfPermission(todaysfunction.this,
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


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);


                String id = c.getString(TAG_ID);

                persons = new HashMap();

                persons.put(TAG_ID, Html.fromHtml(id));

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    todaysfunction.this, personList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.id}
            );

            list.setAdapter(adapter);

            /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, String> map = personList.get(position);
                    String link = map.get(TAG_ID);
                    Log.e("DATAA",link);
                    Intent i=new Intent(getApplicationContext(),Shop.class);
                    i.putExtra("Category",link);
                    startActivity(i);
                }
            });*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                String url="https://abhijeethasabe.000webhostapp.com/todaysfunctions.php";
                Log.e("Lodin",url);
                HttpPost httppost = new HttpPost(url);
                Log.e("urls", String.valueOf(httppost));
                // Depends on your web service
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(personList));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httppost.setHeader("Content-type", "application/json");
                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8000);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
}
