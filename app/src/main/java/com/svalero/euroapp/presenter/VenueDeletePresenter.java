package com.svalero.euroapp.presenter;

import com.svalero.euroapp.contract.VenueDeleteContract;
import com.svalero.euroapp.model.VenueDeleteModel;

public class VenueDeletePresenter implements VenueDeleteContract.Presenter, VenueDeleteContract.Model.OnLoadDeleteOneVenueListener {
    private VenueDeleteContract.View view;
    private VenueDeleteContract.Model model;

    public  VenueDeletePresenter(VenueDeleteContract.View view) {
        this.view = view;
        model = new VenueDeleteModel();
    }
    @Override
    public void deleteOneVenue(long venueId) {
        model.loadDeleteOneVenue(venueId, this);
    }

    @Override
    public void onLoadDeleteOneVenueSuccess() {

    }

    @Override
    public void onLoadDeleteOneVenueError(String message) {
        view.showMessage(message);
    }


}
