package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.ArtistDeleteContract;
import com.svalero.euroapp.model.ArtistDeleteModel;

public class ArtistDeletePresenter implements ArtistDeleteContract.Presenter, ArtistDeleteContract.Model.OnLoadDeleteOneArtistListener {

    private ArtistDeleteContract.View view;
    private ArtistDeleteContract.Model model;

    public ArtistDeletePresenter(ArtistDeleteContract.View view){
        this.view = view;
        model = new ArtistDeleteModel();
    }
    @Override
    public void deleteOneArtist(long artistId) {
        model.loadDeleteOneArtist(artistId, this);
    }

    @Override
    public void onLoadDeleteOneArtistSuccess() {

    }

    @Override
    public void onLoadDeleteOneArtistError(String message) {
        view.showMessage(message);
    }


}
