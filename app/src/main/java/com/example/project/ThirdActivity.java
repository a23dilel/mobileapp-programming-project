package com.example.project;

import android.os.Bundle;
import android.widget.ImageView;
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
        }
    }
}
