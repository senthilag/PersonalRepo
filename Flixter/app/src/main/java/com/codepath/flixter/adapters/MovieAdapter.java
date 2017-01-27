package com.codepath.flixter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixter.R;
import com.codepath.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by senthilg on 1/24/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    Movie movie;
    // View Lookup cache
    private static class ViewHolder {
        ImageView imgView;
        TextView title;
        TextView overview;
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context,android.R.layout.simple_list_item_1,movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if(convertView == null) {
            viewHolder = new ViewHolder();
            int type = getItemViewType(position);

            LayoutInflater inflater =  LayoutInflater.from(getContext());
            if (Integer.compare(type,1) == 0) {
                convertView  = inflater.from(getContext()).inflate(R.layout.movieitem1, parent,false);
                viewHolder.imgView = (ImageView) convertView.findViewById(R.id.imageView1);
                Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.imgView);
            } else {
                // convertView = getInflatedLayoutForType(type,parent);
                convertView = inflater.inflate(R.layout.movieitem, parent, false);
                viewHolder.imgView = (ImageView) convertView.findViewById(R.id.imageView);
                viewHolder.title = (TextView) convertView.findViewById(R.id.mvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.movieOverview);
                viewHolder.title.setText(movie.getOrginalTitle());
                viewHolder.overview.setText(movie.getOverView());
                Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imgView);
            }

            //convertView = inflater.inflate(R.layout.movieitem,parent,false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView);
        imgView.setImageResource(0);
        TextView title = (TextView) convertView.findViewById(R.id.mvTitle);
        TextView overview = (TextView) convertView.findViewById(R.id.movieOverview);
        title.setText(movie.getOrginalTitle());
        overview.setText(movie.getOverView());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(imgView);*/


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getRating();
    }

    // Total number of types is the number of enum values
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type, ViewGroup parent) {
        if (Integer.compare(type,1) == 0) {
            return LayoutInflater.from(getContext()).inflate(R.layout.movieitem1, parent,false);
        } else {
            return null;
        }
    }
}