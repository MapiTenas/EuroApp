package com.svalero.euroapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.svalero.euroapp.domain.FavArtist;

import java.util.List;

@Dao
public interface FavArtistDao {
    @Query("SELECT * FROM favArtist")
    List<FavArtist> getAll();

    @Query("SELECT * FROM favArtist WHERE id = :id")
    FavArtist getFavArtist(String id);

    @Insert
    void insert(FavArtist favArtist);
    @Delete
    void delete (FavArtist favArtist);

}
