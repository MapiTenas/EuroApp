package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.VenueDetailsContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.model.VenueDetailsModel;

public class VenueDetailsPresenter implements VenueDetailsContract.Presenter, VenueDetailsContract.Model.OnLoadOneVenueListener {

    private VenueDetailsContract.View view;
    private VenueDetailsContract.Model model;

    public VenueDetailsPresenter(VenueDetailsContract.View view) {
        this.view = view;
        model = new VenueDetailsModel();
    }

    @Override
    public void loadOneVenue(long venueId) {
        model.loadOneVenue(venueId, this);
    }

    @Override
    public void onLoadOneVenueSuccess(Venue venue) {
        view.listOneVenue(venue);
    }

    @Override
    public void onLoadOneVenueError(String message) {
        view.showMessage(message);
    }

}
