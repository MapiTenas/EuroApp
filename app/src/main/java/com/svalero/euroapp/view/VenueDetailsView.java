package com.svalero.euroapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueDetailsContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueDetailsPresenter;

public class VenueDetailsView extends AppCompatActivity implements VenueDetailsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details_view);
        VenueDetailsContract.Presenter detailsPresenter = new VenueDetailsPresenter(this);


        long venueId = getIntent().getLongExtra("venue_item_id", 1);
        Log.d("VenueDetailsView", "Llega venue_item_id: " + venueId);
        detailsPresenter.loadOneVenue(venueId);
    }

    @Override
    public void listOneVenue(Venue venue) {
        //TextView venueIdView = findViewById(R.id.venue_id_details_tv);
        TextView venueName = findViewById(R.id.venue_name_details_tv);
        TextView cityName = findViewById(R.id.city_name_details_tv1);
        TextView capacityVenue = findViewById(R.id.capacity_details_tv1);
        TextView foundation = findViewById(R.id.inauguration_details_tv1);
        TextView adaptedOrNot = findViewById(R.id.adapted_details_tv1);

        Log.d("VenueDetailsView - Accesible","Es apto para discapacitados: " + venue.isAdapted());

        venueName.setText(venue.getVenueName());
        cityName.setText(venue.getCity());
        capacityVenue.setText(String.valueOf(venue.getCapacity()));
        foundation.setText(venue.getFoundationDate());
        if(venue.isAdapted()){
            adaptedOrNot.setText(R.string.si);
        } else {
            adaptedOrNot.setText(R.string.no);
        }

    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}