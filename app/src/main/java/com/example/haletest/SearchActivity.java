package com.example.haletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ListView listBusinesses;
    private BusinessArrayAdapter bAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        listBusinesses = (ListView) findViewById(R.id.businessList);
        ArrayList<BusinessInfo> aBooks = new ArrayList<BusinessInfo>();
        bAdapter = new BusinessArrayAdapter(this, aBooks);
        listBusinesses.setAdapter(bAdapter);
        fetchBusinesses();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        startActivity(new Intent(SearchActivity.this, HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_search:
                        return true;

                    case R.id.nav_person:
                        startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        private void fetchBusinesses() {
            String name;
            JSONObject searchObject;

            JSONArray array = new JSONArray(yourJsonString);

            for (int i = 0; i < array.length(); i++) {
                JSONObject currObject = array.getJSONObject(i);
                name = currObject.getString("name");

                if(name == "hello")
                {
                    searchObject = currObject
                }
            }

            return searchObject
        }
    }
}
