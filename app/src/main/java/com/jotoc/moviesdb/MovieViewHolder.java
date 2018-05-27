package com.jotoc.moviesdb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

//Create a Movie Holder Class for Movie Adapter.
public class MovieViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    MovieViewHolder(View itemView)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }
}

