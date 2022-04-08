package com.example.haletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BusinessInfoPageActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView name;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_info_page);
        imageView = (ImageView) findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        Businesses bus = (Businesses) getIntent().getSerializableExtra(SearchActivity.BUS_DETAIL_KEY);
        loadBusinessPage(bus);
    }

    // Populate data for the book
    private void loadBusinessPage(Businesses business) {
        //change activity title
        // Populate data
        Glide.with(this).load(business.getImageURL()).into(imageView);
        name.setText(business.getBusinessName());
        description.setText(business.getDescription());
    }
}