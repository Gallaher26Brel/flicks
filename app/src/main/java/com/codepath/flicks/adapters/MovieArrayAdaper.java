package com.codepath.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flicks.R;
import com.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdaper extends ArrayAdapter <Movie> {

    private Context current;

    public MovieArrayAdaper (Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
        this.current = context;
    }

    private static class ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // get data item for position
        Movie movie = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        // check if view is being used
        if (convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.ivImage.setImageResource(0);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        }
        else   {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = current.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackDrop()).into(viewHolder.ivImage);
        }

        return convertView;
    }
}
