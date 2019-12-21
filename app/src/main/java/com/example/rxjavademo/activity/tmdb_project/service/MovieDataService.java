package com.example.rxjavademo.activity.tmdb_project.service;

import com.example.rxjavademo.activity.tmdb_project.model.MovieDBResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {
    @GET("3/movie/popular")
    Observable<MovieDBResponse> getPopularMovies(@Query("api_key") String apikey);
}
