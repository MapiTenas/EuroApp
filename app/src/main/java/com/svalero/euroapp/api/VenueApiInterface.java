package com.svalero.euroapp.api;

import com.svalero.euroapp.domain.Venue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VenueApiInterface {
    @GET("venues")
    Call<List<Venue>> getVenues();

    @POST("venues")
    Call<Venue> addVenue(@Body Venue venue);

    @DELETE("venue/{venueId}")
    Call<Void> deleteVenue(@Path("venueId") long venueId);
}
