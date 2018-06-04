package com.jotoc.moviesdb;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


//Creating Adapter.

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolder viewHolder, int i){
        viewHolder.title.setText(movieList.get(i).getOriginalTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.userRating.setText(vote);

        Glide.with(context)
                .load(movieList.get(i).getPosterPath())
                .into(viewHolder.thumbnail);

    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title, userRating;

        public ImageView thumbnail;

        public ViewHolder(View view){
            super((view));
            title = view.findViewById(R.id.title);
            userRating = view.findViewById(R.id.userrating);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Movie clickedDataItem = movieList.get(position);
                            Intent intent = new Intent(context, DetailActivity.class );
                            intent.putExtra("original_title", movieList.get(position).getOriginalTitle());
                            intent.putExtra("poster_path", movieList.get(position).getPosterPath());
                            intent.putExtra("overview", movieList.get(position).getOverview());
                            intent.putExtra("vote_average",Double.toString(movieList.get(position).getVoteAverage()));
                            intent.putExtra("release_date", movieList.get(position).getReleaseDate());
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                        }
                    }

            });
        }
    }
}


