/**
 * @file MovieDetailsContract.java
 * @brief This is the contract class for Movie details MVP
 * @author Shrikant
 * @date 15/04/2018
 */
package com.example.movieapp.movie_details;

import com.example.movieapp.model.Movie;

public interface MovieDetailsContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(Movie movie);

            void onFailure(Throwable t);
        }

        void getMovieDetails(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(Movie movie);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int movieId);
    }
}
