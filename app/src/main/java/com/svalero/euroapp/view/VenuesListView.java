package com.svalero.euroapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.adapter.VenueAdapter;
import com.svalero.euroapp.contract.VenueListContract;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.VenueListPresenter;

import java.util.ArrayList;
import java.util.List;

public class VenuesListView extends AppCompatActivity implements VenueListContract.View {
    private List<Venue> venues;
    private VenueAdapter adapter;
    private VenueListContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list_view);

        presenter = new VenueListPresenter(this);

        venues = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.venue_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VenueAdapter(venues);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.loadAllVenues();
    }

    @Override
    public void listVenues(List<Venue> venues) {
        this.venues.clear();
        this.venues.addAll(venues);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void goToAddVenue (View view){
        Intent intent = new Intent(this, VenueRegisterView.class);
        startActivity(intent);
    }




}