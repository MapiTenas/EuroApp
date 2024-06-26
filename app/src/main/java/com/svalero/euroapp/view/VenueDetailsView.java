package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueDeleteContract;
import com.svalero.euroapp.contract.VenueDetailsContract;
import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueDeletePresenter;
import com.svalero.euroapp.presenter.VenueDetailsPresenter;

public class VenueDetailsView extends AppCompatActivity implements Style.OnStyleLoaded, VenueDetailsContract.View, VenueDeleteContract.View, VenueEditContract.View {
    private MapView mapView;
    private long venueId;

    VenueDetailsContract.Presenter detailsPresenter = new VenueDetailsPresenter(this);
    VenueDeleteContract.Presenter deletePresenter = new VenueDeletePresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details_view);

        venueId = getIntent().getLongExtra("venue_item_id", 1);
        Log.d("VenueDetailsView", "Llega venue_item_id: " + venueId);

        mapView = findViewById(R.id.mapView_edit);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        detailsPresenter.loadOneVenue(venueId);
    }

    @Override
    public void listOneVenue(Venue venue) {
        TextView venueIdView = findViewById(R.id.venue_id_details_tv);
        TextView venueName = findViewById(R.id.venue_name_details_tv);
        TextView cityName = findViewById(R.id.city_name_details_tv1);
        TextView capacityVenue = findViewById(R.id.capacity_details_tv1);
        TextView foundation = findViewById(R.id.inauguration_details_tv1);
        TextView adaptedOrNot = findViewById(R.id.adapted_details_tv1);
        TextView latitudeText = findViewById(R.id.venue_latitude_details_tv);
        TextView longitudeText = findViewById(R.id.venue_longitude_details_tv);

        Log.d("VenueDetailsView - Accesible","Es apto para discapacitados: " + venue.isAdapted());
        Log.d("VenueDetailsView - Accesible","Latitud: " + venue.getLatitude());
        Log.d("VenueDetailsView - Accesible","Longitud: " + venue.getLongitude());

        venueIdView.setText(String.valueOf(venue.getId()));
        venueName.setText(venue.getVenueName());
        cityName.setText(venue.getCity());
        capacityVenue.setText(String.valueOf(venue.getCapacity()));
        foundation.setText(venue.getFoundationDate());
        if(venue.isAdapted()){
            adaptedOrNot.setText(R.string.si);
        } else {
            adaptedOrNot.setText(R.string.no);
        }
        latitudeText.setText(String.valueOf(venue.getLatitude()));
        longitudeText.setText(String.valueOf(venue.getLongitude()));

        Point point = (Point.fromLngLat(venue.getLongitude(), venue.getLatitude()));
        setCameraPosition(point);

    }

    // Método para mostrar el diálogo de confirmación antes de eliminar el lugar
    public void showDeleteConfirmationDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.esta_seguro);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteVenue();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    // Método para eliminar el lugar
    public void deleteVenue(){
        deletePresenter.deleteOneVenue(venueId);
        showMessage(R.string.la_sede_se_ha_eliminado_correctamente);
        Intent intent = new Intent(VenueDetailsView.this, VenuesListView.class);
        startActivity(intent);
    }

    public void editVenue(View view){

        String venueId = ((TextView) findViewById(R.id.venue_id_details_tv)).getText().toString();
        String venueName = ((TextView) findViewById(R.id.venue_name_details_tv)).getText().toString();
        String city = ((TextView) findViewById(R.id.city_name_details_tv1)).getText().toString();
        String capacity = ((TextView) findViewById(R.id.capacity_details_tv1)).getText().toString();
        String foundationDate = ((TextView) findViewById(R.id.inauguration_details_tv1)).getText().toString();
        String latitude = ((TextView) findViewById(R.id.venue_latitude_details_tv)).getText().toString();
        String longitude = ((TextView) findViewById(R.id.venue_longitude_details_tv)).getText().toString();


        Intent intent = new Intent(this, VenueEditView.class);
        Log.d("VenueDetailsView", "ID del lugar: " + venueId);
        intent.putExtra("venue_id", venueId);
        intent.putExtra("venue_name", venueName);
        intent.putExtra("city", city);
        intent.putExtra("capacity", capacity);
        intent.putExtra("foundation_date", foundationDate);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);

        view.getContext().startActivity(intent);
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
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