package com.svalero.euroapp.presenter;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueRegisterContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.model.VenueRegisterModel;

public class VenueRegisterPresenter implements VenueRegisterContract.Presenter, VenueRegisterContract.Model.OnRegisterVenueListener {
    private VenueRegisterContract.Model model;
    private VenueRegisterContract.View view;

    public VenueRegisterPresenter(VenueRegisterContract.View view){
        this.view = view;
        model = new VenueRegisterModel();
    }

    @Override
    public void registerVenue(Venue venue) {
        model.registerVenue(this, venue);

    }
    @Override
    public void onRegisterVenueSuccess() {
        view.showMessage(R.string.la_sede_se_ha_registrado_correctamente);
    }

    @Override
    public void onRegisterVenueError(String message) {
        view.showMessage(message);
    }


}
