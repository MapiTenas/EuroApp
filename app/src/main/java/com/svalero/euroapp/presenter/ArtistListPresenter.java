package com.svalero.euroapp.presenter;

import android.util.Log;

import com.svalero.euroapp.contract.ArtistListContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.model.ArtistListModel;

import java.util.List;

public class ArtistListPresenter implements ArtistListContract.Presenter, ArtistListContract.Model.OnLoadArtistListener {
    private static final String TAG = "ArtistListPresenter";
    private ArtistListContract.View view;
    private ArtistListContract.Model model;

    public ArtistListPresenter(ArtistListContract.View view){
        this.view = view;
        model = new ArtistListModel();
    }

    @Override
    public void loadAllArtists() {
        Log.d(TAG, "loadAllArtists: Attempting to load all artists...");
        model.loadAllArtists(this);
    }

    @Override
    public void onLoadArtistSuccess(List<Artist> artist) {
        Log.d(TAG, "onLoadArtistSuccess: Received " + artist.size() + " artists from model.");
        view.listArtists(artist);
    }

    @Override
    public void onLoadArtistError(String message) {
        Log.e(TAG, "onLoadArtistError: " + message);
        view.showMessage(message);
    }


}
