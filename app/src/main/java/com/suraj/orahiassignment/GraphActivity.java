package com.suraj.orahiassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.suraj.orahiassignment.graph.CustomGraph;
import com.suraj.orahiassignment.utils.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private CustomGraph graph;
    private Button get,set;
    private ArrayList<Integer> integerArrayList = new ArrayList<>();
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private String con_month;
    private TextView notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        graph = findViewById(R.id.customGraph);
        get = findViewById(R.id.btn_graph_get_data);
        set = findViewById(R.id.btn_graph_show_data);
        notice = findViewById(R.id.text_graph_notice);

        helper = new DatabaseHelper(getApplicationContext());

        int count = helper.getDataCount();
        if (count == 0){
            getData();
        } else {
            notice.setText("Data already present offline");
        }

        get.setOnClickListener( v -> {
            helper.deleteData();
            getData();
        });

        set.setOnClickListener( v->{

            ArrayList<String[]> outputData = helper.getAllData();
            for (String[] str : outputData) {
                integerArrayList.add(Integer.parseInt(str[1]));
                stringArrayList.add(str[0]);
            }
            graph.setHeight(integerArrayList, stringArrayList);

        });


    }

    private void getData() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while we download the data for you...");
        progress.setTitle("Downloading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = "https://demo5636362.mockable.io/stats";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                JSONArray jsonArray = response.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++){
                    String month = jsonArray.getJSONObject(i).getString("month");
                    if (month.length() > 2){
                        con_month = month.substring(0,3);
                    }
                    String stats = jsonArray.getJSONObject(i).getString("stat");
                    helper.addData(con_month,stats);
                    progress.dismiss();

                }

                Log.e("DATA FETCHED","DATA INSERTED");

            }catch (JSONException exception){
                progress.dismiss();
                Log.e("JsonException",exception.toString());
            }
        }, error -> {
            progress.dismiss();
            Log.e("Volley Error", error.getMessage());
        });

        requestQueue.add(jsonObjectRequest);


    }
}


