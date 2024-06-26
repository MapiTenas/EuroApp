package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.ArtistDetailsContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.model.ArtistDetailsModel;

public class ArtistDetailsPresenter implements ArtistDetailsContract.Presenter, ArtistDetailsContract.Model.OnLoadOneArtistListener {

    private ArtistDetailsContract.View view;
    private ArtistDetailsContract.Model model;

    public ArtistDetailsPresenter(ArtistDetailsContract.View view) {
        this.view = view;
        model = new ArtistDetailsModel();
    }

    @Override
    public void loadOneArtist(long artistId) {
        model.loadOneArtist(artistId, this);
    }

    @Override
    public void onLoadOneArtistSuccess(Artist artist) {
        view.listOneArtist(artist);
    }

    @Override
    public void onLoadOneArtistError(String message) {
        view.showMessage(message);
    }


}
