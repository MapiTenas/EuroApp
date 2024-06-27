package com.svalero.euroapp.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavArtist {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String birthDate;
    @ColumnInfo
    private String originCountry;
    @ColumnInfo
    private int publishedCds;
    @ColumnInfo
    private boolean active;
    @ColumnInfo
    private float igFollowers;

    public FavArtist(@NonNull String id, String name, String birthDate, String originCountry, int publishedCds, boolean active, float igFollowers) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        this.publishedCds = publishedCds;
        this.active = active;
        this.igFollowers = igFollowers;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public int getPublishedCds() {
        return publishedCds;
    }

    public void setPublishedCds(int publishedCds) {
        this.publishedCds = publishedCds;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getIgFollowers() {
        return igFollowers;
    }

    public void setIgFollowers(float igFollowers) {
        this.igFollowers = igFollowers;
    }
}
