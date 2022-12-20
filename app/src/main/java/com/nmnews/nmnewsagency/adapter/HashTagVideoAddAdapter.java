package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nmnews.nmnewsagency.R;

import java.util.ArrayList;
import java.util.List;

public class HashTagVideoAddAdapter extends ArrayAdapter<String> {
    // private List<LocationModel> moviesList;
    private List<String> moviesList;
    private List<Integer> imgList;
    private Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public HashTagVideoAddAdapter(Context context, ArrayList<String> users) {
        super(context, 0, users);
        this.moviesList=users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search_peoplebare, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.user_name);
        //  TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(moviesList.get(position));
        // tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}