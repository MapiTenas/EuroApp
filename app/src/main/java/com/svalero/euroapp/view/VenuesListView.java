package com.svalero.euroapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.adapter.VenueAdapter;
import com.svalero.euroapp.contract.VenueListContract;
import com.svalero.euroapp.domain.Edition;
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
        //populateList();
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

    /*private void populateList() {
        List<Edition> editions = new ArrayList<>();
        editions.add(new Edition(1, 24, "XXIV", "Spain", "2024/04/21", "Zaragoza huele a col", true, 5.00, null ));
        venues = new ArrayList<>();
        venues.add(new Venue(1, "La Romareda", "Zaragoza", 120000, "2024/06/17", true, 0.0012, -12.112, editions));

    }*/


}