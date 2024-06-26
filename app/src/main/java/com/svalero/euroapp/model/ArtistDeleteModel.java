package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.ArtistApi;
import com.svalero.euroapp.api.ArtistApiInterface;
import com.svalero.euroapp.contract.ArtistDeleteContract;
import com.svalero.euroapp.domain.Artist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDeleteModel implements ArtistDeleteContract.Model {
    @Override
    public void loadDeleteOneArtist(long artistId, OnLoadDeleteOneArtistListener listener) {
        ArtistApiInterface api = ArtistApi.buildInstance();
        Call<Artist> deleteArtistCall = api.deleteArtistById(artistId);
        deleteArtistCall.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if(response.isSuccessful()){
                    listener.onLoadDeleteOneArtistSuccess();
                } else {
                    String errorMessage = response.message();
                    Log.e("deleteArtistById", "Error al eliminar artist" + errorMessage);
                    listener.onLoadDeleteOneArtistError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.e("deleteArtistById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadDeleteOneArtistError("Se ha producido un error al conectar con el servidor.");
            }
        });
    }
}
