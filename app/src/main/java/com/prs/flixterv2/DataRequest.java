package com.prs.flixterv2;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DataRequest {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String API_URL="https://api.themoviedb.org/3/";
    private AsyncHttpClient client;

    public DataRequest(){
        client = new AsyncHttpClient();
    }

    private String getApiUrl(String url){
        return API_URL + url;
    }

    public void getNowPlayingMovies(AsyncHttpResponseHandler handler){
        String url = getApiUrl("movie/now_playing");
        RequestParams params = new RequestParams();
        params.put("api_key",API_KEY);
        params.put("page",1);
        params.put("language","en-US");
        params.put("region","US");
        client.get(url,params,handler);
    }

    public void getPopularMovies(AsyncHttpResponseHandler handler){
        String url = getApiUrl("movie/popular");
        RequestParams params = new RequestParams();
        params.put("api_key",API_KEY);
        params.put("page",1);
        params.put("language","en-US");
        params.put("region","US");
        client.get(url,params,handler);
    }

    public void getTopRatedMovies(AsyncHttpResponseHandler handler){
        String url = getApiUrl("movie/top_rated");
        RequestParams params = new RequestParams();
        params.put("api_key",API_KEY);
        params.put("page",1);
        params.put("language","en-US");
        params.put("region","US");
        client.get(url,params,handler);
    }

    public void getUpcomingMovies(AsyncHttpResponseHandler handler){
        String url = getApiUrl("movie/upcoming");
        RequestParams params = new RequestParams();
        params.put("api_key",API_KEY);
        params.put("page",1);
        params.put("language","en-US");
        params.put("region","US");
        client.get(url,params,handler);
    }
}
