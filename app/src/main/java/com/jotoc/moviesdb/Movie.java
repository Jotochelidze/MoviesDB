package com.jotoc.moviesdb;
//Creating a Movie Class to represent a single movie.
public class Movie {

    private String movieTitle;
    private String movieDescription;
    private String movieBackdrop;
    private String moviePoster;

    public String getMovieTitle(){
        return movieTitle;
    }

    public void setMovieTitle(String title){
        movieTitle = title;
    }

    public String getMovieDescription(){
        return movieDescription;
    }

    public void setMovieDescription(String description){
        movieDescription = description;
    }

    public String getPoster(){
        return moviePoster;
    }

    public void setPoster(String poster){
        moviePoster = poster;
    }

    public String getMovieBackdrop(){
        return movieBackdrop;
    }

    public void setMovieBackdrop(String backdrop){
        movieBackdrop = backdrop;
    }

}
