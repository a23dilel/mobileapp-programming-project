package com.example.project;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // receive intent value from RecyclerViewAdapter
        Bundle extras = getIntent().getExtras();

        // check if value is not null
        if (extras != null) {

            // Set the title text on the TextView
            String nameValue = extras.getString("name");
            TextView titleTextView = findViewById(R.id.TitleTextView);
            titleTextView.setText(nameValue);

            // load the image on the imageView
            String auxdataValue = extras.getString("auxdata");
            ImageView imageView = findViewById(R.id.imageView);
            Glide.with(this).load(auxdataValue).into(imageView);

            // Set the cost text on the TextView
            String costValue = extras.getString("cost");
            costValue += " kr";
            TextView costTextView = findViewById(R.id.costTextView);
            costTextView.setText(costValue);

            // Get each value
            String idValue = extras.getString("ID");
            String typeValue = extras.getString("type");
            String locationValue = extras.getString("location");
            String categoryValue = extras.getString("category");
            String sizeValue = extras.getString("size");

            // Create TextViews for displaying specific information
            createTextView("ID: " + idValue);
            createTextView("Type: " + typeValue);
            createTextView("Location: " + locationValue);
            createTextView("Category: " + categoryValue);
            createTextView("Size: " + sizeValue);
        }
    }

    public void createTextView(final String key) {

        LinearLayout linearLayout = findViewById(R.id.linearLayout2);

        // grab context textview
        TextView textView = new TextView(this);

        // Set key text from an intent extra to a TextView
        textView.setText(key);

        // set text to center
        textView.setGravity(Gravity.CENTER);

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
