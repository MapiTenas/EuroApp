package com.svalero.euroapp.model;

import android.util.Log;

import com.svalero.euroapp.api.ArtistApi;
import com.svalero.euroapp.api.ArtistApiInterface;
import com.svalero.euroapp.contract.ArtistListContract;
import com.svalero.euroapp.domain.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistListModel implements ArtistListContract.Model {
    @Override
    public void loadAllArtists(OnLoadArtistListener listener) {
        //Preparo la llamada
        ArtistApiInterface api = ArtistApi.buildInstance();
        Call<List<Artist>> getArtistsCall = api.getArtists();
        //Hago la llamada en segundo plano
        getArtistsCall.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                Log.e("getVenuess", response.message());
                List<Artist> artists = response.body();
                listener.onLoadArtistSuccess(artists);
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                Log.e("getArtists", "Error de conexión: " + t.getMessage(), t);
                listener.onLoadArtistError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
