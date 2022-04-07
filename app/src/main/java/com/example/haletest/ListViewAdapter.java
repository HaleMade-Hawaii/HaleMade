package com.example.haletest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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
        this.arraylist = new ArrayList<Businesses>();
        this.arraylist.addAll(businesses);
    }

    public class ViewHolder {
        TextView name;
        TextView description;
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
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.description = (TextView) view.findViewById(R.id.description);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(businesses.get(position).getBusinessName());
        holder.description.setText(businesses.get(position).getDescription());
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

}