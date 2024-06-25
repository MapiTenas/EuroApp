package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mapbox.geojson.Point;
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
import com.svalero.euroapp.contract.VenueRegisterContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueRegisterPresenter;

public class VenueRegisterView extends AppCompatActivity implements Style.OnStyleLoaded, OnMapClickListener, VenueRegisterContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private VenueRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_register);

        mapView = findViewById(R.id.mapView_edit);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

        presenter = new VenueRegisterPresenter(this);
    }

    public void addVenue(View view) {
        EditText etVenueName = findViewById(R.id.venue_name);
        EditText etVenueCity = findViewById(R.id.venue_city);
        EditText etVenueCapacity = findViewById(R.id.venue_capacity);
        EditText etFoundationDate = findViewById(R.id.foundation_date);
        CheckBox adaptedDissabled = findViewById(R.id.adapted);

        String venueName = etVenueName.getText().toString();
        String city = etVenueCity.getText().toString();
        String capacityStr = etVenueCapacity.getText().toString();
        String foundationDate = etFoundationDate.getText().toString();
        boolean adapted = adaptedDissabled.isChecked();

        Log.d("VenueRegisterView", "Venue Name: " + venueName);
        Log.d("VenueRegisterView", "City: " + city);
        Log.d("VenueRegisterView", "Capacity String: " + capacityStr);
        Log.d("VenueRegisterView", "Foundation Date: " + foundationDate);
        Log.d("VenueRegisterView", "Adapted: " + adapted);

        if (venueName.isEmpty() || city.isEmpty() || capacityStr.isEmpty() || foundationDate.isEmpty()) {
            Toast.makeText(this, R.string.completa_campos, Toast.LENGTH_LONG).show();
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.capacidad_valida, Toast.LENGTH_LONG).show();
            return;
        }

        double latitude = currentPoint.latitude();
        double longitude = currentPoint.longitude();

        Venue venue = new Venue(0, venueName, city, capacity, foundationDate, adapted, latitude, longitude);
        presenter.registerVenue(venue);
        Intent intent = new Intent(this, VenuesListView.class);
        startActivity(intent);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

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

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(),point.longitude(), getString(R.string.empty));
        return false;
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}