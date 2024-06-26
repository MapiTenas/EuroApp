package com.svalero.euroapp.api;

import static com.svalero.euroapp.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistApi {
    public static ArtistApiInterface buildInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ArtistApiInterface.class);
    }
}
