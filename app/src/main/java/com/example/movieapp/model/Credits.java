/**
 * @file Credits.java
 * @brief This is a pojo class, which will hold list of casts
 * @author Shrikant
 * @date 16/04/2018
 */
package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {

    @SerializedName("cast")
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
