/**
 * @file Movie.java
 * @brief This is the pojo class for movie data. It will have all attribute related to the movie.
 * @author Shrikant
 * @date 14/04/2018
 */
package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private int id;

    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("poster_path")
    private String thumbPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("credits")
    private Credits credits;

    @SerializedName("runtime")
    private String runTime;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("homepage")
    private String homepage;

    public Movie(int id, String title, String releaseDate, float rating, String thumbPath, String overview, String backdropPath, Credits credits, String runTime, String tagline, String homepage) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.thumbPath = thumbPath;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.credits = credits;
        this.runTime = runTime;
        this.tagline = tagline;
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating=" + rating +
                ", thumbPath='" + thumbPath + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", credits=" + credits +
                ", runTime='" + runTime + '\'' +
                ", tagline='" + tagline + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}
