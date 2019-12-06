package com.example.duana.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.duana.Adapter.SanphamAdapter1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duana.R;
import com.example.duana.model.SanPham;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class TIMKIEM extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public  static  SearchView searchView = null;
    RecyclerView listView;
    ImageView imageView;
    SanphamAdapter1 adapter;
    TextView timkiem,textssuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        // +++++++++++++++++++++++++++++++++++++++++++++++ánh xạ
        timkiem = findViewById(R.id.timkiem);
        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.fishPriceList);
        imageView = findViewById(R.id.backtimkiem);
//        textssuc=findViewById(R.id.textssuc);
//        textssuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchView.onActionViewExpanded();
//                searchView.setQuery(textssuc.getText().toString(),false);
//            }
//        });
        //+++++++++++++++++++++++++++++aa
        SearchManager searchManager = (SearchManager) TIMKIEM.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchView != null) {
            searchView = findViewById(R.id.search);
            searchView.setQueryHint("Bạn cần tìm kiếm......");
        }
        if (searchView != null) {
            assert searchManager != null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(TIMKIEM.this.getComponentName()));
            searchView.setIconified(false);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchManager searchManager = (SearchManager) TIMKIEM.this.getSystemService(Context.SEARCH_SERVICE);
                if (searchView != null) {
                    searchView = findViewById(R.id.search);


                }
                if (searchView != null) {
                    assert searchManager != null;
                    searchView.setSearchableInfo(searchManager.getSearchableInfo(TIMKIEM.this.getComponentName()));
                    searchView.setIconified(false);
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    // Every time when you press search button on keypad an Activity is recreated which in turn calls this function
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onNewIntent(Intent intent) {
        // Get search query and create object of class AsyncFetch

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }
            new AsyncFetch(query).execute();

        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(TIMKIEM.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        AsyncFetch(String searchQuery) {
            this.searchQuery = searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://sanphambanhang.000webhostapp.com/fish-search.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(Objects.requireNonNull(query));
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return ("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();
            ArrayList<SanPham> data = new ArrayList<>();

            pdLoading.dismiss();
            if (result.equals("no rows")) {
                Toast.makeText(TIMKIEM.this, "Không tìm thấy giữ liệu bạn cần", Toast.LENGTH_LONG).show();
            } else {

                try {

                    JSONArray jArray = new JSONArray(result);

                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        SanPham fishData = new SanPham();
                        fishData.Img1 = json_data.getString("img1");
                        fishData.Name_Product = json_data.getString("name_product");
                        fishData.Price_product = json_data.getInt("price_product");
                        fishData.Characteristics = json_data.getString("characteristics");
                        fishData.Ratingbar = json_data.getInt("ratingbar");
                        fishData.Comment = json_data.getString("comment");
                        fishData.Img2 = json_data.getString("img2");
                        fishData.Img3 = json_data.getString("img3");
                        fishData.name1_information = json_data.getString("name1_information");
                        fishData.name2_information = json_data.getString("name2_information");
                        fishData.Information = json_data.getString("information");
                        fishData.Id_Category = json_data.getInt("id_category");
                        fishData.KhaNangLuuTru = json_data.getString("khanangluutru");
                        data.add(fishData);
                        Log.e("av", "onPostExecute: " + data);

                    }


                    // Setup and Handover data to recyclerview
                    listView = findViewById(R.id.fishPriceList);
                    adapter = new SanphamAdapter1(TIMKIEM.this, data);
                    listView.setAdapter(adapter);
                    listView.setLayoutManager(new LinearLayoutManager(TIMKIEM.this));
                    listView.setLayoutManager(new GridLayoutManager(TIMKIEM.this, 2));
                    Log.d("âbc", "onPostExecute: " + adapter);

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(TIMKIEM.this, result, Toast.LENGTH_LONG).show();
                    Log.d("abc", "onPostExecute: " + result);

                }

                data.equals(result);
            }

        }

    }

}
