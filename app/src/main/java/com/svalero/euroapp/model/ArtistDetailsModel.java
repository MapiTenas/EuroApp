package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.ArtistApi;
import com.svalero.euroapp.api.ArtistApiInterface;
import com.svalero.euroapp.contract.ArtistDetailsContract;
import com.svalero.euroapp.domain.Artist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDetailsModel implements ArtistDetailsContract.Model {
    @Override
    public void loadOneArtist(long artistId, OnLoadOneArtistListener listener) {
        ArtistApiInterface api = ArtistApi.buildInstance();
        Call<Artist> getArtistCall = api.getArtistById(artistId);

        getArtistCall.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if(response.isSuccessful()){
                    Artist artist = response.body();
                    listener.onLoadOneArtistSuccess(artist);
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
                        listener.onLoadOneArtistError("Error en la respuesta del servidor: " + errorMessage);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.e("getVenueById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneArtistError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
