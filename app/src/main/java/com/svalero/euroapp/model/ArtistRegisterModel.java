package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.ArtistApi;
import com.svalero.euroapp.api.ArtistApiInterface;
import com.svalero.euroapp.contract.ArtistRegisterContract;
import com.svalero.euroapp.domain.Artist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistRegisterModel implements ArtistRegisterContract.Model {
    @Override
    public void registerArtist(OnRegisterArtistListener listener, Artist artist) {
        ArtistApiInterface api = ArtistApi.buildInstance();
        Call<Artist> addArtistCall = api.addArtist(artist);

        addArtistCall.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                listener.onRegisterArtistSuccess();
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.e("addArtist", t.getMessage());
                listener.onRegisterArtistError("No se ha podido conectar con el servidor");
            }
        });
    }
}
