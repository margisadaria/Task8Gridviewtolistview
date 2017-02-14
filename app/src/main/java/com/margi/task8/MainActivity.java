package com.margi.task8;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    int getid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new myclass().execute("http://rapidans.esy.es/test/getallcat.php");

    }

    class myclass extends AsyncTask<String,Void,String> {

        ProgressDialog dialog;
        ArrayList<Post> postArrayList = new ArrayList<>();
        Customadaptergridview adapter;

        Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            //dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<String> postlist = new ArrayList<String>();

            try {

                JSONObject jsonObject1 = new JSONObject(s);


                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    Post p = new Post();
                    p.setId(jsonObject.getInt("id"));

                    p.setName(jsonObject.getString("name"));

                    postArrayList.add(p);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            adapter = new Customadaptergridview(MainActivity.this, postArrayList);

            gridView = (GridView) findViewById(R.id.main_gridview);

            gridView.setAdapter(adapter);

            gridView.setBackgroundColor(Color.GRAY);
            gridView.setVerticalSpacing(2);
            gridView.setHorizontalSpacing(2);


//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Bundle basket=new Bundle();
////                    basket.getInt("pos",position);
//                    Intent a= new Intent(MainActivity.this,MainActivity2.class);
////                    a.putExtra()
//                    a.putExtra("pos",position);
//                    startActivity(a);
//                }
//            });
//a        }a

        }
    }



}

