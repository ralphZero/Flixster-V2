package com.prs.flixterv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prs.flixterv2.Models.Movie;
import com.prs.flixterv2.R;

import java.util.ArrayList;
import java.util.List;

public class PosterOnlyAdapter extends RecyclerView.Adapter<PosterOnlyAdapter.PosterHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public PosterOnlyAdapter(Context mContext) {
        this.mContext = mContext;
        this.movieList = new ArrayList<>();
    }

    public void addToList(List<Movie> list){
        movieList.clear();
        movieList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PosterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.poster_item,parent,false);
        return new PosterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(mContext)
                .load(movie.getPoster_path())
                .placeholder(R.drawable.placeholder_poster)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class PosterHolder extends RecyclerView.ViewHolder {

        ImageView image;

        PosterHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgPoster);
        }
    }
}
