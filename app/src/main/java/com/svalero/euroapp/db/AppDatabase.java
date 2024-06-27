package com.svalero.euroapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.euroapp.domain.FavArtist;

@Database(entities = {FavArtist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavArtistDao favArtistDao();
}
