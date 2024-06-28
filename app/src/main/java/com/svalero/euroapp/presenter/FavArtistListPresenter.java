package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.FavArtistListContract;
import com.svalero.euroapp.domain.FavArtist;
import com.svalero.euroapp.model.FavArtistListModel;
import com.svalero.euroapp.view.FavArtistListView;

import java.util.List;

public class FavArtistListPresenter implements FavArtistListContract.Presenter, FavArtistListContract.Model.OnLoadFavArtistListener {

    private FavArtistListView view;
    private FavArtistListModel model;

    public FavArtistListPresenter (FavArtistListView view){
        this.view = view;
        model = new FavArtistListModel(view);
    }

    @Override
    public void loadAllFavArtist() {
        model.loadAllFavArtist(this);
    }
    @Override
    public void onLoadFavArtistSuccess(List<FavArtist> favArtists) {
        view.listFavArtist(favArtists);
    }

    @Override
    public void onLoadFavArtistError(String message) {
        view.showMessage(message);
    }


}
