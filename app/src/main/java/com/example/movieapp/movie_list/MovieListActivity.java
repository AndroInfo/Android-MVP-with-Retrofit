/**
 * @file MovieListActivity.java
 * @brief This activity is responsible for showing all movies data in the list format.
 * @author Shrikant
 * @date 14/04/2018
 */

package com.example.movieapp.movie_list;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.movieapp.R;
import com.example.movieapp.adapter.MoviesAdapter;
import com.example.movieapp.model.Movie;
import com.example.movieapp.movie_details.MovieDetailsActivity;
import com.example.movieapp.movie_filter.MovieFilterActivity;
import com.example.movieapp.utils.GridSpacingItemDecoration;


import static com.example.movieapp.utils.Constants.ACTION_MOVIE_FILTER;
import static com.example.movieapp.utils.Constants.KEY_MOVIE_ID;
import static com.example.movieapp.utils.Constants.KEY_RELEASE_FROM;
import static com.example.movieapp.utils.Constants.KEY_RELEASE_TO;
import static com.example.movieapp.utils.GridSpacingItemDecoration.dpToPx;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View, MovieItemClickListener,
        ShowEmptyView {

    private static final String TAG = "MovieListActivity";
    private MovieListPresenter movieListPresenter;
    private RecyclerView rvMovieList;
    private List<Movie> moviesList;
    private MoviesAdapter moviesAdapter;
    private ProgressBar pbLoading;
    private FloatingActionButton fabFilter;
    private TextView tvEmptyView;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;

    // Constants for filter functionality
    private String fromReleaseFilter = "";
    private String toReleaseFilter = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        getSupportActionBar().setTitle(getString(R.string.most_popular_movies));
        initUI();

        setListeners();

        //Initializing presenter
        movieListPresenter = new MovieListPresenter(this);

        movieListPresenter.requestDataFromServer();
    }

    /**
     * This method will initialize the UI components
     */
    private void initUI() {

        rvMovieList = findViewById(R.id.rv_movie_list);

        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, moviesList);

        mLayoutManager = new GridLayoutManager(this, 2);
        rvMovieList.setLayoutManager(mLayoutManager);
        rvMovieList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(this, 10), true));
        rvMovieList.setItemAnimator(new DefaultItemAnimator());
        rvMovieList.setAdapter(moviesAdapter);

        pbLoading = findViewById(R.id.pb_loading);

        fabFilter = findViewById(R.id.fab_filter);

        tvEmptyView = findViewById(R.id.tv_empty_view);
    }

    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvMovieList.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    movieListPresenter.getMoreData(pageNo);
                    loading = true;
                }

                // Hide and show Filter button
                if (dy > 0 && fabFilter.getVisibility() == View.VISIBLE) {
                    fabFilter.hide();
                } else if (dy < 0 && fabFilter.getVisibility() != View.VISIBLE) {
                    fabFilter.show();
                }
            }
        });

        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Going to filter screen
                Intent movieFilterIntent = new Intent(MovieListActivity.this, MovieFilterActivity.class);
                movieFilterIntent.putExtra(KEY_RELEASE_FROM, fromReleaseFilter);
                movieFilterIntent.putExtra(KEY_RELEASE_TO, toReleaseFilter);
                startActivityForResult(movieFilterIntent, ACTION_MOVIE_FILTER);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTION_MOVIE_FILTER) {
            if (resultCode == Activity.RESULT_OK) {
                // Checking if there is any data to filter
                fromReleaseFilter = data.getStringExtra(KEY_RELEASE_FROM);
                toReleaseFilter = data.getStringExtra(KEY_RELEASE_TO);

                moviesAdapter.setFilterParameter(fromReleaseFilter, toReleaseFilter);
                moviesAdapter.getFilter().filter("");
            }
        }
    }

    @Override
    public void showProgress() {

        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieArrayList) {

        moviesList.addAll(movieArrayList);
        moviesAdapter.notifyDataSetChanged();

        // This will help us to fetch data from next page no.
        pageNo++;
    }


    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
    }

    @Override
    public void onMovieItemClick(int position) {

        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this, MovieDetailsActivity.class);
        detailIntent.putExtra(KEY_MOVIE_ID, moviesList.get(position).getId());
        startActivity(detailIntent);
    }

    @Override
    public void showEmptyView() {

        rvMovieList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvMovieList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }
}
