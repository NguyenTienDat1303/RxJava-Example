package com.example.rxjavademo.activity.tmdb_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.tmdb_project.MovieDetailActivity;
import com.example.rxjavademo.activity.tmdb_project.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context mContext, List<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.title.setText(mMovies.get(position).getTitle());
        holder.rate.setText(String.valueOf(mMovies.get(position).getVoteAverage()));
        String imgPath = "https://image.tmdb.org/t/p/w500"+mMovies.get(position).getPosterPath();
        Glide.with(mContext)
                .load(imgPath)
                .placeholder(R.drawable.loading)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, rate;
        ImageView img;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ivMovie);
            rate = itemView.findViewById(R.id.tvRating);
            title = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Movie selectedMovie = mMovies.get(position);
                        Intent intent = new Intent(mContext, MovieDetailActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        mContext.startActivities(new Intent[]{intent});
                    }

                }
            });
        }
    }
}
