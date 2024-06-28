package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.adapter.FavArtistAdapter;
import com.svalero.euroapp.contract.FavArtistListContract;
import com.svalero.euroapp.domain.FavArtist;
import com.svalero.euroapp.presenter.FavArtistListPresenter;

import java.util.ArrayList;
import java.util.List;

public class FavArtistListView extends AppCompatActivity implements FavArtistListContract.View {

    private List<FavArtist> favArtists;
    private FavArtistAdapter adapter;
    private FavArtistListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs_list_view);

        presenter = new FavArtistListPresenter(this);

        favArtists = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.favs_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavArtistAdapter(favArtists);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.loadAllFavArtist();
    }

    @Override
    public void listFavArtist(List<FavArtist> favArtists) {
        this.favArtists.clear();
        this.favArtists.addAll(favArtists);
        adapter.notifyDataSetChanged();
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