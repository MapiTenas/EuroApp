package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.VenueApi;
import com.svalero.euroapp.api.VenueApiInterface;
import com.svalero.euroapp.contract.VenueDeleteContract;
import com.svalero.euroapp.domain.Venue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueDeleteModel implements VenueDeleteContract.Model {
    @Override
    public void loadDeleteOneVenue(long venueId, OnLoadDeleteOneVenueListener listener) {
        VenueApiInterface api = VenueApi.buildInstance();
        Call<Venue> deleteVenueCall = api.deleteVenueById(venueId);
        deleteVenueCall.enqueue(new Callback<Venue>() {
            @Override
            public void onResponse(Call<Venue> call, Response<Venue> response) {
                if (response.isSuccessful()) {
                    listener.onLoadDeleteOneVenueSuccess();
                } else {
                    String errorMessage = response.message();
                    Log.e("deleteVenueById", "Error al eliminar venue" + errorMessage);
                    listener.onLoadDeleteOneVenueError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Venue> call, Throwable t) {
                Log.e("deleteVenueById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadDeleteOneVenueError("Se ha producido un error al conectar con el servidor.");
            }
        });
    }
}
