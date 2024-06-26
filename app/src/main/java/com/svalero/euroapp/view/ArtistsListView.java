package com.svalero.euroapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.adapter.ArtistAdapter;
import com.svalero.euroapp.contract.ArtistListContract;
import com.svalero.euroapp.domain.Artist;

import com.svalero.euroapp.presenter.ArtistListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ArtistsListView extends AppCompatActivity implements ArtistListContract.View {

    private List<Artist> artists;
    private ArtistAdapter adapter;
    private ArtistListContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list_view);

        presenter = new ArtistListPresenter(this);

        artists = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.artist_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArtistAdapter(artists);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.loadAllArtists();
    }


    @Override
    public void listArtists(List<Artist> artistsRes) {
        Log.d("ArtistsListView", "Received " + artistsRes.size() + " artists from presenter.");
        this.artists.clear();
        this.artists.addAll(artistsRes);
        adapter.notifyDataSetChanged();

    }

    public void goToAddArtist(View view){
        Intent intent = new Intent(this, ArtistRegisterView.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}