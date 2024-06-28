package com.svalero.euroapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
}