package com.svalero.euroapp.model;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.api.VenueApi;
import com.svalero.euroapp.api.VenueApiInterface;
import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueEditModel implements VenueEditContract.Model {

    private Context context;

    public VenueEditModel(Context context){
        this.context = context;
    }


    @Override
    public void loadEditOneVenue(long venueId, Venue venue, OnLoadEditOneVenueListener listener) {
        VenueApiInterface api = VenueApi.buildInstance();
        Call<Venue> editVenueCall = api.editVenueById(venueId, venue);
        editVenueCall.enqueue(new Callback<Venue>() {
            @Override
            public void onResponse(Call<Venue> call, Response<Venue> response) {
                if (response.code() == HTTP_OK) {
                    Log.e("editVenue", response.message());
                    listener.onLoadEditOneVenueSuccess();
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_LONG).show();
                } else {
                    String errorMessage = parseError(response);
                    Log.e("editVenue", "Error en la respuesta: " + errorMessage);
                    listener.onLoadEditOneVenueError("Error en la respuesta del servidor: " + errorMessage);

                }
            }
            @Override
            public void onFailure(Call<Venue> call, Throwable t) {
                if(t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {
                    Log.e("editVenue", "Error en la solicitud: " + t.getMessage());
                    listener.onLoadEditOneVenueError("Se ha producido un error al conectar con el servidor");
                }

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
