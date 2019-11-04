package com.prs.flixterv2.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.prs.flixterv2.Adapters.BackdropAdapter;
import com.prs.flixterv2.Adapters.PosterOnlyAdapter;
import com.prs.flixterv2.DataRequest;
import com.prs.flixterv2.Models.Movie;
import com.prs.flixterv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoviesFragment extends Fragment {

    @BindView(R.id.rvUpcoming)
    RecyclerView rvUpcoming;
    @BindView(R.id.rvNowPlayingMovie)
    RecyclerView rvNowPlaying;
    @BindView(R.id.rvPopular)
    RecyclerView rvPopular;
    @BindView(R.id.rvTopRated)
    RecyclerView rvTopRated;

    private BackdropAdapter nowPlayingAdapter;
    private PosterOnlyAdapter upcomingAdapter;
    private PosterOnlyAdapter popularAdapter;
    private PosterOnlyAdapter topRatedAdapter;
    private DataRequest dataRequest;
    private static final String TAG = "MoviesFragment";

    private int page;
    public static MoviesFragment newInstance(int page, String title) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putInt("pageNo", page);
        args.putString("pageTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            page = getArguments().getInt("pageNo");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataRequest = new DataRequest();

        //Discover
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvNowPlaying);
        upcomingAdapter = new PosterOnlyAdapter(getActivity());
        rvUpcoming.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvUpcoming.setAdapter(upcomingAdapter);
        //Now Playing
        nowPlayingAdapter = new BackdropAdapter(getActivity());
        rvNowPlaying.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvNowPlaying.setAdapter(nowPlayingAdapter);
        //Popular
        popularAdapter = new PosterOnlyAdapter(getActivity());
        rvPopular.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvPopular.setAdapter(popularAdapter);
        //Top Rated
        topRatedAdapter = new PosterOnlyAdapter(getActivity());
        rvTopRated.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvTopRated.setAdapter(topRatedAdapter);

        getNowPlayingMovieData();
        getUpcomingMovieData();
        getPopularMovieData();
        getTopRatedMovieData();

    }

    private void getPopularMovieData() {
        dataRequest.getPopularMovies(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("results");
                    List<Movie> movies = new ArrayList<>();
                    for (int i=0; i < 10; i++){
                        Movie movie = Movie.fromJson(array.getJSONObject(i));
                        movies.add(movie);
                    }
                    popularAdapter.addToList(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"error "+e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(responseString.contentEquals(""))
                    Log.d(TAG,"Error: "+responseString);
            }
        });
    }
    private void getTopRatedMovieData() {
        dataRequest.getTopRatedMovies(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("results");
                    List<Movie> movies = new ArrayList<>();
                    for (int i=0; i < 10; i++){
                        Movie movie = Movie.fromJson(array.getJSONObject(i));
                        movies.add(movie);
                    }
                    topRatedAdapter.addToList(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"error "+e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(responseString.contentEquals(""))
                    Log.d(TAG,"Error: "+responseString);
            }
        });
    }
    private void getUpcomingMovieData() {
        dataRequest.getUpcomingMovies(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("results");
                    List<Movie> movies = new ArrayList<>();
                    for (int i=0; i < 10; i++){
                        Movie movie = Movie.fromJson(array.getJSONObject(i));
                        movies.add(movie);
                    }
                    upcomingAdapter.addToList(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"error "+e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(responseString.contentEquals(""))
                    Log.d(TAG,"Error: "+responseString);
            }
        });
    }
    private void getNowPlayingMovieData(){
        dataRequest.getNowPlayingMovies(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("results");
                    List<Movie> movies = new ArrayList<>();
                    for (int i=0; i < 10; i++){
                        Movie movie = Movie.fromJson(array.getJSONObject(i));
                        movies.add(movie);
                    }
                    nowPlayingAdapter.addToList(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG,"error "+e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if(errorResponse!=null)
                    Log.d(TAG,"Error: "+errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if(responseString.contentEquals(""))
                    Log.d(TAG,"Error: "+responseString);
            }
        });
    }
}
