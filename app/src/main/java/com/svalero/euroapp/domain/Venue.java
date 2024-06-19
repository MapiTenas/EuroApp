package com.svalero.euroapp.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class Venue {
    @PrimaryKey
    private @NonNull long id;
    @ColumnInfo
    private String venueName;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private int capacity;
    @ColumnInfo
    private String foundationDate;
    @ColumnInfo
    private boolean adapted;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;

    public Venue (long id, String venueName, String city, int capacity, String foundationDate, boolean adapted, double latitude, double longitude){
        this.id = id;
        this.venueName = venueName;
        this.city = city;
        this.capacity = capacity;
        this.foundationDate = foundationDate;
        this.adapted = adapted;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    public boolean isAdapted() {
        return adapted;
    }

    public void setAdapted(boolean adapted) {
        this.adapted = adapted;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}
