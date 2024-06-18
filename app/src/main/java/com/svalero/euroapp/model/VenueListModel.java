package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.VenueApi;
import com.svalero.euroapp.api.VenueApiInterface;
import com.svalero.euroapp.contract.VenueListContract;
import com.svalero.euroapp.domain.Venue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueListModel implements VenueListContract.Model {
    @Override
    public void loadAllVenues(OnLoadVenueListener listener) {
        //Preparo la llamada
        VenueApiInterface api = VenueApi.buildInstance();
        Call<List<Venue>> getVenuesCall = api.getVenues();
        //Hago la llamada en segundo plano
        getVenuesCall.enqueue(new Callback<List<Venue>>() {
            @Override
            public void onResponse(Call<List<Venue>> call, Response<List<Venue>> response) {
                Log.e("getVenuess", response.message());
                List<Venue> venues = response.body();
                listener.onLoadVenueSuccess(venues);
            }

            @Override
            public void onFailure(Call<List<Venue>> call, Throwable t) {
                Log.e("getVenues", t.getMessage());
                listener.onLoadVenueError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
