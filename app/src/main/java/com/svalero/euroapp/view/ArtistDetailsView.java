package com.svalero.euroapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.ArtistDeleteContract;
import com.svalero.euroapp.contract.ArtistDetailsContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.presenter.ArtistDeletePresenter;
import com.svalero.euroapp.presenter.ArtistDetailsPresenter;

public class ArtistDetailsView extends AppCompatActivity implements ArtistDetailsContract.View, ArtistDeleteContract.View {

    private long artistId;
    ArtistDeleteContract.Presenter deletePresenter = new ArtistDeletePresenter(this);
    ArtistDetailsContract.Presenter detailsPresenter = new ArtistDetailsPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details_view);


        artistId = getIntent().getLongExtra("artist_item_id",1);
        Log.d("ArtistDetailsView", "Llega artist_item_id: " + artistId);

        detailsPresenter.loadOneArtist(artistId);
    }

    @Override
    public void listOneArtist(Artist artist) {
        TextView artistIdView = findViewById(R.id.artist_id_details_tv);
        TextView artistNameDetail = findViewById(R.id.artist_name_details_tv);
        TextView artistBirthDateDetail = findViewById(R.id.birthdate_details_tv1);
        TextView artistOriginCountryDetail = findViewById(R.id.originCountry_details_tv1);
        TextView artistCdDetail = findViewById(R.id.published_cd_details_tv1);
        TextView artistInstagramDetail = findViewById(R.id.instagram_details_tv1);
        TextView activeOrNot = findViewById(R.id.active_details_tv1);

        artistIdView.setText(String.valueOf(artist.getId()));
        artistNameDetail.setText(artist.getName());
        artistBirthDateDetail.setText(artist.getBirthDate());
        artistOriginCountryDetail.setText(artist.getOriginCountry());
        artistCdDetail.setText(String.valueOf(artist.getPublishedCds()));
        artistInstagramDetail.setText(String.valueOf(artist.getIgFollowers()));
        if(artist.isActive()){
            activeOrNot.setText(R.string.si);
        } else {
            activeOrNot.setText(R.string.no);
        }
    }

    public void showDeleteConfirmationDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.esta_seguro);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteArtist();
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

    public void deleteArtist(){
        deletePresenter.deleteOneArtist(artistId);
        showMessage(R.string.el_artista_se_ha_eliminado_correctamente);
        Intent intent = new Intent(ArtistDetailsView.this, ArtistsListView.class);
        startActivity(intent);
    }

    public void editArtist(View view){
        String artistId = ((TextView) findViewById(R.id.artist_id_details_tv)).getText().toString();
        String name = ((TextView) findViewById(R.id.artist_name_details_tv)).getText().toString();
        String birthDate = ((TextView) findViewById(R.id.birthdate_details_tv1)).getText().toString();
        String originCountry = ((TextView) findViewById(R.id.originCountry_details_tv1)).getText().toString();
        String publishedCds = ((TextView) findViewById(R.id.published_cd_details_tv1)).getText().toString();
        String igFollowers = ((TextView) findViewById(R.id.instagram_details_tv1)).getText().toString();

        Intent intent = new Intent(this, ArtistEditView.class);
        intent.putExtra("artist_id", artistId);
        intent.putExtra("name", name);
        intent.putExtra("birthDate", birthDate);
        intent.putExtra("originCountry", originCountry);
        intent.putExtra("publishedCds", publishedCds);
        intent.putExtra("igFollowers", igFollowers);

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
}