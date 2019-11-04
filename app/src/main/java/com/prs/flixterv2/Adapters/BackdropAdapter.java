package com.prs.flixterv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prs.flixterv2.Models.Movie;
import com.prs.flixterv2.R;

import java.util.ArrayList;
import java.util.List;

public class BackdropAdapter extends RecyclerView.Adapter<BackdropAdapter.MovieHolder> {

    private Context mContext;
    private List<Movie> moviesList;

    public BackdropAdapter(Context context){
        mContext = context;
        moviesList = new ArrayList<>();
    }

    public void addToList(List<Movie> list){
        moviesList.clear();
        moviesList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.backdrop_item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = moviesList.get(position);
        Glide.with(mContext)
                .load(movie.getBackdrop_path())
                .placeholder(R.drawable.placeholder_wide)
                .into(holder.image);
        holder.text.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemBackdrop);
            text = itemView.findViewById(R.id.itemTitle);
        }
    }
}
