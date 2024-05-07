package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

            // if filter is same thing "ShowAll" then print all keys and values otherwise print specific key and value
            if (filter.equals("ShowAll"))
            {
                for (int i = 0; i < objects.size(); i++)
                {
                    createTextView(dataList.get(position).getEachKeyAndValue(i, filter));
                }
            }
            else
            {
                for (int i = 0; i < objects.size(); i++)
                {
                    if (dataList.get(position).getEachValue(i).contains(filter))
                    {
                        createTextView(dataList.get(position).getEachKeyAndValue(i, filter));
                    }
                }
            }
        }

        public void createTextView(final String data)
        {

            // grab context and store to textview
            TextView textView = new TextView(context);

            // set text for adding key and value
            textView.setText(data);

            // store key and value to intent
            //intent.putExtra(key, dataList.get(getAdapterPosition()).getSpecificValue(key));

            textView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    context.startActivity(intent);
                }
            });

            // Add the TextView to the linearLayout
            linearLayout.addView(textView);
        }
    }
}
