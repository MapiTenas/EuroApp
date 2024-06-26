package com.svalero.euroapp.domain;

public class Artist {
    private long id;
    private String name;
    private String birthDate;
    private String originCountry;
    private int publishedCds;
    private boolean active;
    private double igFollowers;
    public Artist(long id, String name, String birthDate, String originCountry, int publishedCds, boolean active, double igFollowers) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        this.publishedCds = publishedCds;
        this.active = active;
        this.igFollowers = igFollowers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getIgFollowers() {
        return igFollowers;
    }

    public void setIgFollowers(double igFollowers) {
        this.igFollowers = igFollowers;
    }
}
