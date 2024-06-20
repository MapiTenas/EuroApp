package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueDetailsContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueDetailsPresenter;

public class VenueDetailsView extends AppCompatActivity implements Style.OnStyleLoaded, VenueDetailsContract.View {
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details_view);
        VenueDetailsContract.Presenter detailsPresenter = new VenueDetailsPresenter(this);

        long venueId = getIntent().getLongExtra("venue_item_id", 1);
        Log.d("VenueDetailsView", "Llega venue_item_id: " + venueId);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

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
        Log.d("VenueDetailsView - Accesible","Latitud: " + venue.getLatitude());
        Log.d("VenueDetailsView - Accesible","Longitud: " + venue.getLongitude());

        venueName.setText(venue.getVenueName());
        cityName.setText(venue.getCity());
        capacityVenue.setText(String.valueOf(venue.getCapacity()));
        foundation.setText(venue.getFoundationDate());
        if(venue.isAdapted()){
            adaptedOrNot.setText(R.string.si);
        } else {
            adaptedOrNot.setText(R.string.no);
        }

        Point point = (Point.fromLngLat(venue.getLongitude(), venue.getLatitude()));
        setCameraPosition(point);

    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(16.0)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
}