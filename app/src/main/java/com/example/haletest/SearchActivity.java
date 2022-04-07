package com.example.haletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    Businesses[] businesses;
    ArrayList<Businesses> arrayList = new ArrayList<Businesses>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        businesses = new Businesses[3];
        businesses[0] = new Businesses("Hawaii Doggie Bakery",
                "We are Hawaii’s original bakery for dogs, founded in 1998, handcrafting " +
                        "fresh baked healthy innovative treat for dogs using quality local Hawaiian " +
                        "ingredients!\n");
        businesses[1] = new Businesses("Purve Donut Shop",
                "Life Changing Donuts Made Fresh To Order!");

        list = (ListView) findViewById(R.id.listview);

        arrayList.add(businesses[0]);
        arrayList.add(businesses[1]);

        // Pass results to com.example.haletest.ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrayList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

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
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
