package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class SecondActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
    EditText editText;
    private String filter;
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    // Grab URL from API and store in a variable
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23dilel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Grab string value from editText
                filter = editText.getText().toString();

                // add string value and commit.
                myPreferenceEditor.putString("data1", filter);
                myPreferenceEditor.commit();

                // JSONTask does checking if JSON_URL is validate, network and then active the function onPostExecute().
                new JsonTask(SecondActivity.this).execute(JSON_URL);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        // editText grab "ShowAll" value
        editText = findViewById(R.id.editText);
        filter = editText.getText().toString();

        myPreferenceRef = getSharedPreferences("preference", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        // trying to find preference data and if data was found then set "ShowALL" value on editText.
        editText.setText(myPreferenceRef.getString("data1", filter));

        // JSONTask does checking if JSON_URL is validate, network and then active the function onPostExecute().
        new JsonTask(SecondActivity.this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {

        // if json isn't null then run code normal otherwise print error message
        if (json != null)
        {
            // Check if JSON is not NULL
            try {

                // Take JSON-data and turn into an array
                JSONArray arr = new JSONArray(json);

                // Take a Data class which can hold of arrays
                ArrayList<Data> dataList = new ArrayList<>();

                // Each object get string of specific name and add each name on constructor from data.
                for (int i = 0; i < arr.length(); i++)
                {
                    JSONObject object = arr.getJSONObject(i);

                    dataList.add(new Data(object));
                }

                // RecyclerView grab recycler_view from activity_main.xml so att kan print data on the layout.
                RecyclerView recyclerView = findViewById(R.id.recycler_view);

                // Before showing data on the layout, must have an adapter which can bind data (mountains) and print out on the linear layout.
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dataList, myPreferenceRef.getString("data1", filter));
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                // After that adapter send a message about data has changed.
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                Log.e("MainActivity==>","E:"+e.getMessage());
            }
        }
        else
        {
            Toast.makeText(this, "Failed to load json", Toast.LENGTH_SHORT).show();
        }
    }
}
