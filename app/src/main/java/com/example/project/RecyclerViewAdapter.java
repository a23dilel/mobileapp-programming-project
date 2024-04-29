package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Data> dataList;
    public RecyclerViewAdapter(Context context, ArrayList<Data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    // This one does inflate the layout for example giving a look to our rows
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view);
    }

    // this one does give values to the views which created in the layout file
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        //TODO: make a for-loop
        holder.createTextView(dataList.get(position).getField("ID"));
    }

    // this one does show how many views is there to show displayed
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // grab the views from layout file (layout.xml) almost like onCreate
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // grab id linearLayout from xml
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

        public void createTextView(String data) {
            // grab context textview
            TextView textView = new TextView(itemView.getContext());

            // set text for the textView
            textView.setText(data);

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
    }
}
