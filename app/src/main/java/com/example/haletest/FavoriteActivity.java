package com.example.haletest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// Favorites with Firebase Reference: https://www.youtube.com/watch?v=1d2zUZR7Hqs by Atif Pervaiz
public class FavoriteActivity extends AppCompatActivity  {

    ListView list;

    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;

    ListViewAdapter adapter;
    Businesses[] businesses;
    ArrayList<Businesses> arrayList = new ArrayList<>();
    public static final String BUS_DETAIL_KEY = "business";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        businesses = new Businesses[3];
        businesses[0] = new Businesses("Hawaii Doggie Bakery",
                "We are Hawaii’s original bakery for dogs, founded in 1998, handcrafting " +
                        "fresh baked healthy innovative treat for dogs using quality local Hawaiian " +
                        "ingredients!\n", "https://i.imgur.com/YfOW14C.jpg", "2961 E Manoa Rd, Honolulu, HI 96822",
                "Wed, Fri–Sun, 10AM-3PM", "https://hawaiidoggiebakery.org/");
        businesses[1] = new Businesses("Purve Donut Shop",
                "Life Changing Donuts Made Fresh To Order!", "https://i.imgur.com/Jr15ebj.jpg",
                "1234 Kona St, Honolulu, HI 96814", "Mon-Thurs 6AM-2PM, Fri-Sun 6AM-5PM", "https://www.purvehawaii.com/");
        businesses[2] = new Businesses("Lanikai Bath & Body",
                "Made fresh and all natural, Lanikai Bath and Body reflects the Hawaii of today, beautiful, light-hearted and cosmopolitan.",
                "https://i.imgur.com/QOIfPvJ.png",
                "600 Kailua Road. No. 119 Kailua, Hawaii 96734", "Mon-Sat 10AM-5PM, Sun 10AM-4PM", "https://lanikaibathandbody.com/");

        list = (ListView) findViewById(R.id.listview);

        adapter = new ListViewAdapter(this, arrayList);
        list.setAdapter(adapter);

        firebaseAuth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid()).child("Favorites");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                HashMap<String, Object> value = (HashMap<String, Object>) snapshot.getValue();
                String temp = (String) value.get("placeId");
                for (Businesses wp : businesses) {
                    if (wp.getBusinessName().equals(temp)) {
                        arrayList.add(wp);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> value = (HashMap<String, Object>) snapshot.getValue();
                String temp = (String) value.get("placeId");
                for (Businesses wp : businesses) {
                    if (wp.getBusinessName().equals(temp)) {
                        arrayList.remove(wp);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setupBusinessSelectedListener();
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_person);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(FavoriteActivity.this, HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_search:
                        startActivity(new Intent(FavoriteActivity.this, SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_person:
                        startActivity(new Intent(FavoriteActivity.this, ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    public void setupBusinessSelectedListener() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the detail view passing book as an extra
                Intent intent = new Intent(FavoriteActivity.this, BusinessInfoPageActivity.class);
                intent.putExtra(BUS_DETAIL_KEY, adapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
