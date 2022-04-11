package com.example.haletest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Businesses> businesses = null;
    private ArrayList<Businesses> arraylist;

    public ListViewAdapter(Context context, List<Businesses> businesses) {
        mContext = context;
        this.businesses = businesses;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(businesses);
    }

    public class ViewHolder {
        TextView name;
        TextView description;
        ImageView image;
    }

    @Override
    public int getCount() {
        return businesses.size();
    }

    @Override
    public Businesses getItem(int position) {
        return businesses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_items, null);
            holder.name = view.findViewById(R.id.name);
            holder.description = view.findViewById(R.id.description);
            holder.image = view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(businesses.get(position).getBusinessName());
        holder.description.setText(businesses.get(position).getDescription());
        Glide.with(view.getContext()).load(businesses.get(position).getImageURL()).into(holder.image);
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        businesses.clear();
        if (charText.length() == 0) {
            businesses.addAll(arraylist);
        } else {
            for (Businesses wp : arraylist) {
                if (wp.getBusinessName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    businesses.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void checkIsFavorite(String uuid) {
        businesses.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        for (Businesses wp: arraylist) {
            ref.child(uuid).child("Favorites").child(wp.getBusinessName())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean isInMyFavorite = snapshot.exists();
                            if (isInMyFavorite) {
                                businesses.add(wp);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        notifyDataSetChanged();
    }

}