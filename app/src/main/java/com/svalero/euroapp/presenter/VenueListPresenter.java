package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.VenueListContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.model.VenueListModel;

import java.util.List;

public class VenueListPresenter implements VenueListContract.Presenter, VenueListContract.Model.OnLoadVenueListener {

    private VenueListContract.View view;
    private VenueListContract.Model model;

    public VenueListPresenter(VenueListContract.View view) {
        this.view = view;
        model = new VenueListModel();
    }

    @Override
    public void loadAllVenues() {
        model.loadAllVenues(this);
    }

    @Override
    public void onLoadVenueSuccess(List<Venue> venue) {
        view.listVenues(venue);
    }

    @Override
    public void onLoadVenueError(String message) {
        view.showMessage(message);
    }


}
