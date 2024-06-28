package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueEditPresenter;

import java.util.Objects;

public class VenueEditView extends AppCompatActivity implements Style.OnStyleLoaded, OnMapClickListener, VenueEditContract.View {
    private VenueEditContract.Presenter editPresenter;
    private long venueId;
    private String editVenueName;
    private String editCity;
    private String editCapacity;
    private String editFoundation;
    double editLongitude;
    double editLatitude;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private Point currentPoint;
    private GesturesPlugin gesturesPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_edit_view);
        editPresenter = new VenueEditPresenter(this, this);
        Intent intent = getIntent();

        venueId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("venue_id")));
        editVenueName = intent.getStringExtra("venue_name");
        editCity = intent.getStringExtra("city");
        editCapacity = intent.getStringExtra("capacity");
        editFoundation = intent.getStringExtra("foundation_date");
        editLatitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("latitude")));
        editLongitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("longitude")));


        EditText venueNameView = findViewById(R.id.edit_venue_name);
        EditText venueCityView = findViewById(R.id.edit_venue_city);
        EditText venueCapacityView = findViewById(R.id.edit_venue_capacity);
        EditText venueFoundationView = findViewById(R.id.edit_venue_foundation);
        EditText venueLatitudeView = findViewById(R.id.edit_venue_latitude);
        EditText venueLongitudeView = findViewById(R.id.edit_venue_longitude);
        CheckBox checkAdapted = findViewById(R.id.adapted_edit);


        venueNameView.setText(editVenueName);
        venueCityView.setText(editCity);
        venueCapacityView.setText(editCapacity);
        venueFoundationView.setText(editFoundation);
        venueLatitudeView.setText(String.valueOf(editLatitude));
        venueLongitudeView.setText(String.valueOf(editLongitude));

        mapView = findViewById(R.id.mapView_edit);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();
        Point point = (Point.fromLngLat(editLongitude, editLatitude));
        setCameraPosition(point);

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    public void editThisVenue(View view) {
        EditText editVenueName = findViewById(R.id.edit_venue_name);
        EditText editVenueCity = findViewById(R.id.edit_venue_city);
        EditText editVenueCapacity = findViewById(R.id.edit_venue_capacity);
        EditText editVenueFoundation = findViewById(R.id.edit_venue_foundation);
        EditText editVenueLatitude = findViewById(R.id.edit_venue_latitude);
        EditText editVenueLongitude = findViewById(R.id.edit_venue_longitude);
        CheckBox checkAdapted = findViewById(R.id.adapted_edit);

        String venueName = editVenueName.getText().toString().trim();
        String venueCity = editVenueCity.getText().toString().trim();
        String venueCapacity = editVenueCapacity.getText().toString().trim();
        String venueFoundation = editVenueFoundation.getText().toString().trim();
        String venueLatitudeText = editVenueLatitude.getText().toString().trim();
        String venueLongitudeText = editVenueLongitude.getText().toString().trim();

        if (venueName.isEmpty() || venueCity.isEmpty() || venueCapacity.isEmpty() || venueFoundation.isEmpty() || venueLatitudeText.isEmpty() || venueLongitudeText.isEmpty()) {
            showMessage(R.string.completa_campos);
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(venueCapacity);
        } catch (NumberFormatException e) {
            showMessage(R.string.capacidad_valida);
            return;
        }

        double latitude;
        try {
            latitude = Double.parseDouble(venueLatitudeText);
        } catch (NumberFormatException e) {
            showMessage(R.string.latlong_validos);
            return;
        }

        double longitude;
        try {
            longitude = Double.parseDouble(venueLongitudeText);
        } catch (NumberFormatException e) {
            showMessage(R.string.latlong_validos);
            return;
        }

        boolean adapted = checkAdapted.isChecked();

        Venue updatedVenue = new Venue(venueId, venueName, venueCity, capacity, venueFoundation, adapted, latitude, longitude);
        editPresenter.editOneVenue(venueId, updatedVenue);

        Intent intent = new Intent(this, VenuesListView.class);
        startActivity(intent);
    }


    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude(), getString(R.string.empty));
        EditText venueLatitude = findViewById(R.id.edit_venue_latitude);
        venueLatitude.setText(String.valueOf(point.latitude()));
        EditText venueLongitude = findViewById(R.id.edit_venue_longitude);
        venueLongitude.setText(String.valueOf(point.longitude()));
        return false;
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addMarker(double latitude, double longitude, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker))
                .withTextField(title);
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(15.0)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }


    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //Código relacionado con el actionbar y su funcionamiento
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_list_venues) {
            Intent intent = new Intent(this, VenuesListView.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.action_list_artist) {
            Intent intent = new Intent(this, ArtistsListView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_add_venue) {
            Intent intent = new Intent(this, VenueRegisterView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_add_artist) {
            Intent intent = new Intent(this, ArtistRegisterView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_list_favs) {
            Intent intent = new Intent(this, FavArtistListView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}