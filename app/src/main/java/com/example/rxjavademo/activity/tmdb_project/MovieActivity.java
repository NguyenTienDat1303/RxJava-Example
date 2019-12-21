package com.example.rxjavademo.activity.tmdb_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.tmdb_project.adapter.MovieAdapter;
import com.example.rxjavademo.activity.tmdb_project.model.Movie;
import com.example.rxjavademo.activity.tmdb_project.model.MovieDBResponse;
import com.example.rxjavademo.activity.tmdb_project.service.MovieDataService;
import com.example.rxjavademo.activity.tmdb_project.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private Observable<MovieDBResponse> movieDBResponseObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");
        movies = new ArrayList<>();
        showOnRecyclerView();
        getPopularMovies();
        swipeRefreshLayout = findViewById(R.id.swipeLayour);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    private void showOnRecyclerView() {
        recyclerView = findViewById(R.id.rcMovie);
        movieAdapter = new MovieAdapter(this, movies);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
    }

    private void getPopularMovies() {

        MovieDataService movieDataService = RetrofitInstance.getService();
        movieDBResponseObservable = movieDataService.getPopularMovies(
                getString(R.string.movie_api_key)
        );
        compositeDisposable.add(
                movieDBResponseObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Function<MovieDBResponse, ObservableSource<Movie>>() {
                            @Override
                            public ObservableSource<Movie> apply(MovieDBResponse movieDBResponse) throws Exception {
                                Log.i("onNext", "flatMap" + movieDBResponse.getMovies().toArray(new Movie[0]).length);

                                return Observable.fromArray(movieDBResponse.getMovies().toArray(new Movie[0]));
                            }
                        })
                        .concatMap(new Function<Movie, ObservableSource<Movie>>() {
                            @Override
                            public ObservableSource<Movie> apply(Movie movie) throws Exception {
                                return Observable.just(movie).delay(1, TimeUnit.SECONDS);
                            }
                        })
                        .filter(new Predicate<Movie>() {
                            @Override
                            public boolean test(Movie movie) throws Exception {
                                return movie.getVoteAverage()>7;
                            }
                        })
                        .subscribeWith(new DisposableObserver<Movie>() {
                            @Override
                            public void onNext(Movie movie) {
                                Log.i("onNext", movie.getTitle() + ": " + movie.getVoteAverage());
//                                Toast.makeText(MovieActivity.this, ""+movie.getTitle(), Toast.LENGTH_SHORT).show();
                                movies.add(movie);
                                movieAdapter.notifyItemInserted(movies.size());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
