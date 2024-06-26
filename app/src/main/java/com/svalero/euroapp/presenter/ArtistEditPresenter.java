package com.svalero.euroapp.presenter;

import android.content.Context;

import com.svalero.euroapp.contract.ArtistEditContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.model.ArtistEditModel;

public class ArtistEditPresenter implements ArtistEditContract.Presenter, ArtistEditContract.Model.OnLoadEditOneArtistListener {

    private ArtistEditContract.View view;
    private ArtistEditContract.Model model;

    public ArtistEditPresenter(ArtistEditContract.View view, Context context){
        this.view = view;
        this.model = new ArtistEditModel(context);
    }

    @Override
    public void editOneArtist(long artistId, Artist artist) {
        model.loadEditOneArtist(artistId, artist,this);
    }

    @Override
    public void onLoadEditOneArtistSuccess() {

    }

    @Override
    public void onLoadEditOneArtistError(String message) {
        view.showMessage(message);
    }

}
