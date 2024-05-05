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
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Data> dataList;

    private String filter;
    public RecyclerViewAdapter(Context context, ArrayList<Data> dataList, String filter){
        this.context = context;
        this.dataList = dataList;
        this.filter = filter;
    }

    // This one does inflate the layout for example giving a look to our rows
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view, context, dataList);
    }

    // this one does give values to the views which created in the layout file
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        // when user scroll down or upp will clear all views
        holder.clearViews();

        // get all keys
        List<String> keys = dataList.get(position).getAllKeysInOrder();

        // for-loop each key
        for (String key : keys)
        {
            // if filter is same thing "ShowAll" then print all keys and values otherwise print specific key and value
            if (Objects.equals(filter, "ShowAll"))
            {
                holder.createTextView(key);
            }
            else
            {
                // if filter same thing key then print key and value
                if (key.equals(filter))
                {
                    holder.createTextView(key);
                }
            }
        }
    }

    // this one does show how many views is there to show displayed
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // grab the views from layout file (layout.xml) almost like onCreate
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private Context context;
        private ArrayList<Data> dataList;
        private Intent intent;

        public MyViewHolder(@NonNull View itemView, Context context, ArrayList<Data> dataList) {
            super(itemView);

            // grab id linearLayout from xml
            linearLayout = itemView.findViewById(R.id.linearLayout);
            // grab context from recyclerViewAdapter
            this.context = context;

            // grab dataList from recyclerViewAdapter
            this.dataList = dataList;

            // when startActivity starts will call ThirdActivity.class
            intent = new Intent(context, ThirdActivity.class);
        }

        public void createTextView(final String key) {

            // grab context and store to textview
            TextView textView = new TextView(context);

            // set text for adding key and value
            textView.setText(dataList.get(getAdapterPosition()).getField(key));

            // store key and value to intent
            intent.putExtra(key, dataList.get(getAdapterPosition()).getSpecificValue(key));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(intent);
                }
            });

            // create a width and height layout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            // then add the width and height layout to the textView
            textView.setLayoutParams(layoutParams);
            // Add the TextView to the linearLayout
            linearLayout.addView(textView);
        }

        // remove existing views before adding new ones
        public void clearViews() {
            linearLayout.removeAllViews();
        }
    }
}
