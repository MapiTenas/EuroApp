package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.VenueApi;
import com.svalero.euroapp.api.VenueApiInterface;
import com.svalero.euroapp.contract.VenueDetailsContract;
import com.svalero.euroapp.domain.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueDetailsModel implements VenueDetailsContract.Model {
    @Override
    public void loadOneVenue(long venueId, OnLoadOneVenueListener listener) {
        VenueApiInterface api = VenueApi.buildInstance();
        Call<Venue> getVenueCall = api.getVenueById(venueId);

        getVenueCall.enqueue(new Callback<Venue>() {
            @Override
            public void onResponse(Call<Venue> call, Response<Venue> response) {
                if (response.isSuccessful()) {
                    Venue venue = response.body();
                    listener.onLoadOneVenueSuccess(venue);
            } else {
                    String errorMessage = "Error en la respuesta";
                    if (response.errorBody() != null){
                        try {
                            JSONObject errorJson = new JSONObject(response.errorBody().string());
                            errorMessage = errorJson.optString("message", errorMessage);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("getVenueById", "Error en la respuesta: " + errorMessage);
                        listener.onLoadOneVenueError("Error en la respuesta del servidor: " + errorMessage);
                    }
                }
            }

            @Override
            public void onFailure(Call<Venue> call, Throwable t) {
                Log.e("getVenueById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneVenueError("Se ha producido un error al conectar con el servidor");
            }
        });

    }
}
