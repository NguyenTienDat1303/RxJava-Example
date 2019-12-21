package com.example.rxjavademo.activity.tmdb_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.tmdb_project.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    Movie movie;
    ImageView imgMovie;
    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgMovie = findViewById(R.id.ivMovieLarge);
        movieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        movieSynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);
        movieRating = (TextView) findViewById(R.id.tvMovieRating);
        movieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = (Movie) getIntent().getSerializableExtra("movie");
            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
            String imgPath = "https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
            Glide.with(this)
                    .load(imgPath)
                    .placeholder(R.drawable.loading)
                    .into(imgMovie);
            getSupportActionBar().setTitle(movie.getTitle());

            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(String.valueOf(movie.getVoteAverage()));
            movieReleaseDate.setText(movie.getReleaseDate());
        }
    }
}
