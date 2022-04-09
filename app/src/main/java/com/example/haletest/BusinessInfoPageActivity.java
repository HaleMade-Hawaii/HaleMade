package com.example.haletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusinessInfoPageActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView name;
    private TextView description;
    private TextView location;
    private ImageButton favButton;
    private String placeId;

    boolean isInMyFavorite = false;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_info_page);

//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() != null) {
//            checkIsFavorite();
//        }
        imageView = (ImageView) findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        location = (TextView) findViewById(R.id.location);
        favButton = (ImageButton) findViewById(R.id.favButton);

//        placeId = name.getText().toString();

        Businesses bus = (Businesses) getIntent().getSerializableExtra(SearchActivity.BUS_DETAIL_KEY);
        loadBusinessPage(bus);

        placeId = name.getText().toString();

        favButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isInMyFavorite) {
                    favButton.setImageResource(R.drawable.favorite_turned_in);
                    RegisterUser.addToFavorite(BusinessInfoPageActivity.this, placeId);
                    isInMyFavorite = !isInMyFavorite;
                } else {
                    favButton.setImageResource(R.drawable.favorite_turned_out);
                    RegisterUser.removeFromFavorite(BusinessInfoPageActivity.this, placeId);
                    isInMyFavorite = !isInMyFavorite;
                }
            }
        });
    }

    // Populate data for the book
    private void loadBusinessPage(Businesses business) {
        //change activity title
        // Populate data
        Glide.with(this).load(business.getImageURL()).into(imageView);
        name.setText(business.getBusinessName());
        description.setText(business.getDescription());
        location.setText(business.getLocation());
    }

    private void checkIsFavorite() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid()).child("Favorites").child(placeId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        isInMyFavorite = snapshot.exists();
                        if (isInMyFavorite) {
                            favButton.setImageResource(R.drawable.favorite_turned_in);
                        } else {
                            favButton.setImageResource(R.drawable.favorite_turned_out);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}