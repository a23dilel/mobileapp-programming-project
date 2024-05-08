package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private final Context context;
    private final ArrayList<Data> dataList;
    private final String filter;

    public RecyclerViewAdapter(Context context, ArrayList<Data> dataList, String filter)
    {
        this.context = context;
        this.dataList = dataList;
        this.filter = filter;
    }

    // This one does inflate the layout for example giving a look to our rows
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view);
    }

    // this one does give values to the views which created in the layout file
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position)
    {
        try {
            holder.bindData(position);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    // grab the views from layout file (layout.xml) almost like onCreate
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final LinearLayout linearLayout;
        private final Intent intent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // grab id linearLayout from xml
            linearLayout = itemView.findViewById(R.id.linearLayout);

            // when startActivity starts will call ThirdActivity.class
            intent = new Intent(context, ThirdActivity.class);
        }

        public void bindData(int position) throws JSONException {
            // when user scroll down or upp will clear all views
            linearLayout.removeAllViews();

            List<JSONObject> objects = dataList.get(position).getJSONObjects();
            createTextView(objects);
        }

        public void createTextView(final List<JSONObject> objects) throws JSONException
        {
            List<String> objectKeysAndValues = new ArrayList<>();
            for (int i = 0; i < objects.size(); i++) {
                JSONObject object = objects.get(i);

                for (int j = 0; j < object.length(); j++) {
                    String key = object.names().get(j).toString();
                    String value = object.get(key).toString();
                    objectKeysAndValues.add(key + ": " + value);
                }
            }

            for (int j = 0; j < objectKeysAndValues.size(); j++)
            {
                final String keyAndValue = objectKeysAndValues.get(j);

                // Check if the key contains the substring
                if (Objects.equals(filter, "ShowAll") || keyAndValue.contains(filter))
                {
                    // grab context and store to textview
                    final TextView textView = new TextView(context);

                    // set text for adding key and value
                    textView.setText(keyAndValue);

                    // Add the TextView to the linearLayout
                    linearLayout.addView(textView);

                    textView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            // Get the text of the clicked TextView
                            String clickedValue = textView.getText().toString();

                            for (int i = 0; i < objects.size(); i++)
                            {
                                JSONObject object = objects.get(i);

                                List<String> objectKeysAndValues = new ArrayList<>();
                                for (int j = 0; j < object.length(); j++)
                                {
                                    try {
                                        String key = object.names().get(j).toString();
                                        String value = object.get(key).toString();
                                        objectKeysAndValues.add(key + ": " + value);
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                                if (objectKeysAndValues.contains(clickedValue))
                                {
                                    for (int j = 0; j < objectKeysAndValues.size(); j++)
                                    {
                                        String keyAndValue = objectKeysAndValues.get(j);
                                        String[] parts = keyAndValue.split(": ", 2);

                                        if (parts.length == 2) {
                                            String key = parts[0];
                                            String value = parts[1];

                                            Log.d("hhhh", key + " " + value);

                                            intent.putExtra(key, value);
                                        }
                                    }
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
