package com.svalero.euroapp.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Edition {
    @PrimaryKey
    private @NonNull long id;
    @ColumnInfo
    private Integer editionNum;
    @ColumnInfo
    private String romanNumeralEdition;
    @ColumnInfo
    private  String countryOrganizer;
    @ColumnInfo
    private String finalDate;
    @ColumnInfo
    private String slogan;
    @ColumnInfo
    private boolean cancelled;
    @ColumnInfo
    private double totalBudget;
    @ColumnInfo
    private Venue venue;
    //@ColumnInfo
    //private List<Song> songs;

    public Edition(long id, Integer editionNum, String romanNumeralEdition, String countryOrganizer, String finalDate, String slogan, boolean cancelled, double totalBudget, Venue venue) {
        this.id = id;
        this.editionNum = editionNum;
        this.romanNumeralEdition = romanNumeralEdition;
        this.countryOrganizer = countryOrganizer;
        this.finalDate = finalDate;
        this.slogan = slogan;
        this.cancelled = cancelled;
        this.totalBudget = totalBudget;
        this.venue = venue;
    }
}
