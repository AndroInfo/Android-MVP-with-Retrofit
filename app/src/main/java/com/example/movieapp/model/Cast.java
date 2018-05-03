/**
 * @file Cast.java
 * @brief This is the pojo class, it contains all the data for Movie cast
 * @author Shrikant
 * @date 16/04/2018
 */
package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("cast_id")
    private int castId;

    @SerializedName("character")
    private String character;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    public Cast(int castId, String character, String name, String profilePath) {
        this.castId = castId;
        this.character = character;
        this.name = name;
        this.profilePath = profilePath;
    }

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId +
                ", character='" + character + '\'' +
                ", name='" + name + '\'' +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
