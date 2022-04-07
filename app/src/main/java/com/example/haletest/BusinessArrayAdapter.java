package com.example.haletest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BusinessArrayAdapter extends ArrayAdapter<BusinessInfo> {

    public BusinessArrayAdapter(Context context, ArrayList<BusinessInfo> businesses) {
        super(context, 0, businesses);
    }

    private static class ViewHolder {
        public ImageView businessImage;
        public TextView name;
        public TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final BusinessInfo businesses = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.business_item, parent, false);
            viewHolder.businessImage = (ImageView)convertView.findViewById(R.id.businessImage);
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.description = (TextView)convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(businesses.getName());
        viewHolder.description.setText(businesses.getName());
        Picasso.with(getContext()).load(Uri.parse(businesses.getImageURL())).into(viewHolder.businessImage);
        // Return the completed view to render on screen
        return convertView;
    }
}