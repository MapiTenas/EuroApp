package com.svalero.euroapp.model;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.api.ArtistApi;
import com.svalero.euroapp.api.ArtistApiInterface;
import com.svalero.euroapp.contract.ArtistEditContract;
import com.svalero.euroapp.domain.Artist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistEditModel implements ArtistEditContract.Model {

    private Context context;
    public ArtistEditModel(Context context){
        this.context = context;
    }

    @Override
    public void loadEditOneArtist(long artistId, Artist artist, OnLoadEditOneArtistListener listener) {
        ArtistApiInterface api = ArtistApi.buildInstance();
        Call<Artist> editArtistCall = api.editArtistById(artistId, artist);

        editArtistCall.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if (response.code() == HTTP_OK) {
                    Log.e("editArtist", response.message());
                    listener.onLoadEditOneArtistSuccess();
                    Toast.makeText(context, R.string.modificacion_exitosa, Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_LONG).show();
                } else {
                    String errorMessage = parseError(response);
                    Log.e("editArtist", "Error en la respuesta: " + errorMessage);
                    listener.onLoadEditOneArtistError("Error en la respuesta del servidor: " + errorMessage);

                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {

            }
        });
    }

    private String parseError(Response<?> response) {
        try {
            JSONObject errorJson = new JSONObject(response.errorBody().string());
            return errorJson.optString("message", context.getString(R.string.error_desconocido));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return context.getString(R.string.respuesta_error);
        }
    }
}
