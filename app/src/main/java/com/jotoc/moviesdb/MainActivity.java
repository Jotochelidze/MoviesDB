package com.jotoc.moviesdb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MovieAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog pd;
    private static final String LOG_TAG = MovieAdapter.class.getName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.main_content)
    SwipeRefreshLayout swipeContainer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();


        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching movies...");
        pd.setCancelable(false);
        pd.show();
        pd.dismiss();
        ButterKnife.bind(this);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);


        //Setting number of columns in GridLayout for Portrait and Landscape orientations;
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON();

    }

    private void loadJSON() {


        MovieApiClient Client = new MovieApiClient();
        MovieApiService apiService =
                Client.getClient().create(MovieApiService.class);
        Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(getApplicationContext(), movies));
                recyclerView.smoothScrollToPosition(0);
                if (swipeContainer.isRefreshing()) {
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(MainActivity.this, "Error fetching data!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

