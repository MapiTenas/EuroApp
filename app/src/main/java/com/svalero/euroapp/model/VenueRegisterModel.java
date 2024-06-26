package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.VenueApi;
import com.svalero.euroapp.api.VenueApiInterface;
import com.svalero.euroapp.contract.VenueRegisterContract;
import com.svalero.euroapp.domain.Venue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueRegisterModel implements VenueRegisterContract.Model {
    @Override
    public void registerVenue(OnRegisterVenueListener listener, Venue venue) {
        VenueApiInterface api = VenueApi.buildInstance();
        Call<Venue> addVenueCall = api.addVenue(venue);

        addVenueCall.enqueue(new Callback<Venue>() {
            @Override
            public void onResponse(Call<Venue> call, Response<Venue> response) {
                listener.onRegisterVenueSuccess();
            }

            @Override
            public void onFailure(Call<Venue> call, Throwable t) {
                Log.e("addVenue", t.getMessage());
                listener.onRegisterVenueError("No se ha podido conectar con el servidor");

            }
        });

    }
}
