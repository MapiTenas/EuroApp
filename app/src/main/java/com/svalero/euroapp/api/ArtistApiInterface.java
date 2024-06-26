package com.svalero.euroapp.api;

import com.svalero.euroapp.domain.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtistApiInterface {
    @GET("artists")
    Call<List<Artist>> getArtists();

    @GET("artist/{artistId}")
    Call<Artist> getArtistById(@Path("artistId") long artistId);

    @POST("artists")
    Call<Artist> addArtist(@Body Artist artist);

    @DELETE("artist/{artistId}")
    Call<Artist> deleteArtistById(@Path("artistId") long artistId);

    @PUT("artist/{artistId}")
    Call<Artist> editArtistById(@Path("artistId") long artistId, @Body Artist artist);

}
