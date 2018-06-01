package com.jotoc.moviesdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


//Creating Adapter.

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private Context context;


    public MovieAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = new ArrayList<>();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        // Using Picasso to load images from the internet.
        Picasso.with(context)
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (movieList == null) ? 0 : movieList.size();
    }

    public void setMovieList(List<Movie> movieList)
    {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        // Letting know the adapter that data has changed.
        notifyDataSetChanged();
    }
}
