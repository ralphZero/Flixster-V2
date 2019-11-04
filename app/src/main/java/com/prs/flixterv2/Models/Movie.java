package com.prs.flixterv2.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String id;
    private String poster_path;
    private String title;
    private String overview;
    private String backdrop_path;
    private String vote_average;
    private String popularity;
    private String release_date;

    public static Movie fromJson(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setId(jsonObject.getString("id"));
        movie.setPoster_path(jsonObject.getString("poster_path"));
        movie.setTitle(jsonObject.getString("title"));
        movie.setOverview(jsonObject.getString("overview"));
        movie.setBackdrop_path(jsonObject.getString("backdrop_path"));
        movie.setVote_average(jsonObject.getString("vote_average"));
        movie.setPopularity(jsonObject.getString("popularity"));
        movie.setRelease_date(jsonObject.getString("release_date"));
        return movie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",poster_path);
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdrop_path);
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
