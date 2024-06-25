package com.svalero.euroapp.presenter;

import android.content.Context;

import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.model.VenueEditModel;

public class VenueEditPresenter implements VenueEditContract.Presenter, VenueEditContract.Model.OnLoadEditOneVenueListener {

    private VenueEditContract.View view;
    private VenueEditContract.Model model;

    public VenueEditPresenter(VenueEditContract.View view, Context context){
        this.view = view;
        this.model = new VenueEditModel(context);
    }

    @Override
    public void editOneVenue(long venueId, Venue venue) {
        model.loadEditOneVenue(venueId, venue, this);
    }

    @Override
    public void onLoadEditOneVenueSuccess() {

    }

    @Override
    public void onLoadEditOneVenueError(String message) {
        view.showMessage(message);
    }


}
