package com.svalero.euroapp.presenter;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.ArtistRegisterContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.model.ArtistRegisterModel;

public class ArtistRegisterPresenter implements ArtistRegisterContract.Presenter, ArtistRegisterContract.Model.OnRegisterArtistListener {
    private ArtistRegisterContract.Model model;
    private ArtistRegisterContract.View view;

    public ArtistRegisterPresenter(ArtistRegisterContract.View view){
        this.view = view;
        model = new ArtistRegisterModel();
    }

    @Override
    public void registerArtist(Artist artist) {
        model.registerArtist(this, artist);
    }


    @Override
    public void onRegisterArtistSuccess() {
        view.showMessage(R.string.el_artista_se_ha_registrado_correctamente);
    }

    @Override
    public void onRegisterArtistError(String message) {
        view.showMessage(message);
    }


}
