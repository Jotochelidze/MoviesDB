package com.jotoc.moviesdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<MoviesResponse>getPopularMovies(@Query("api_key=d83f97303639f8234a75dfdc0cb1626b")String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse>getTopRatedMovies(@Query("api_key=d83f97303639f8234a75dfdc0cb1626b")String apiKey);
}
