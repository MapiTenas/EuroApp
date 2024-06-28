package com.svalero.euroapp.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.euroapp.contract.FavArtistListContract;
import com.svalero.euroapp.db.AppDatabase;
import com.svalero.euroapp.domain.FavArtist;

import java.util.List;

public class FavArtistListModel implements FavArtistListContract.Model {

    public Context context;
    public FavArtistListModel(Context context){

        this.context = context;
    }

    @Override
    public void loadAllFavArtist(OnLoadFavArtistListener listener) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "favArtistDao").allowMainThreadQueries().build();
        List<FavArtist> favArtists = db.favArtistDao().getAll();
        listener.onLoadFavArtistSuccess(favArtists);
    }
}
