package com.svalero.euroapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.ArtistEditContract;
import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.presenter.ArtistEditPresenter;

import java.util.Objects;

public class ArtistEditView extends AppCompatActivity implements ArtistEditContract.View {
    private ArtistEditContract.Presenter editPresenter;

    private long artistId;
    private String editName;
    private String editBirthDate;
    private String editOriginCountry;
    private String editPublishedCds;
    private double editIgFollowers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_edit_view);
        editPresenter = new ArtistEditPresenter(this, this);
        Intent intent = getIntent();

        artistId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("artist_id")));
        editName = intent.getStringExtra("name");
        editBirthDate = intent.getStringExtra("birthDate");
        editOriginCountry = intent.getStringExtra("originCountry");
        editPublishedCds = intent.getStringExtra("publishedCds");
        editIgFollowers = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("igFollowers")));

        EditText artistNameView = findViewById(R.id.edit_artist_name);
        EditText artistBirthDayView = findViewById(R.id.edit_artist_birthDate);
        EditText artistOriginCountryView = findViewById(R.id.edit_artist_originCountry);
        EditText artistPublishedCdsView = findViewById(R.id.edit_artist_publishedCds);
        EditText artistIgFollowersView = findViewById(R.id.edit_artist_igfollowers);

        artistNameView.setText(editName);
        artistBirthDayView.setText(editBirthDate);
        artistOriginCountryView.setText(editOriginCountry);
        artistPublishedCdsView.setText(editPublishedCds);
        artistIgFollowersView.setText(String.valueOf(editIgFollowers));
    }

    public void editThisArtist(View view) {
        EditText artistNameView = findViewById(R.id.edit_artist_name);
        EditText artistBirthDateView = findViewById(R.id.edit_artist_birthDate);
        EditText artistOriginCountryView = findViewById(R.id.edit_artist_originCountry);
        EditText artistPublishedCdsView = findViewById(R.id.edit_artist_publishedCds);
        EditText artistIgFollowersView = findViewById(R.id.edit_artist_igfollowers);
        CheckBox checkActive = findViewById(R.id.checkActive);

        String artistName = artistNameView.getText().toString().trim();
        String artistBirthDate = artistBirthDateView.getText().toString().trim();
        String artistOriginCountry = artistOriginCountryView.getText().toString().trim();
        String artistPublishedCds = artistPublishedCdsView.getText().toString().trim();
        String artistIgFollowersText = artistIgFollowersView.getText().toString().trim();

        if (artistName.isEmpty() || artistBirthDate.isEmpty() || artistOriginCountry.isEmpty() || artistPublishedCds.isEmpty() || artistIgFollowersText.isEmpty()) {
            showMessage(R.string.completa_campos);
            return;
        }

        int publishedCds;
        try {
            publishedCds = Integer.parseInt(artistPublishedCds);
        } catch (NumberFormatException e) {
            showMessage(R.string.cd_valido);
            return;
        }

        double igFollowers;
        try {
            igFollowers = Double.parseDouble(artistIgFollowersText);
        } catch (NumberFormatException e) {
            showMessage(R.string.igfollowers_validos);
            return;
        }

        boolean active = checkActive.isChecked();

        Artist updatedArtist = new Artist(artistId, artistName, artistBirthDate, artistOriginCountry, publishedCds, active, igFollowers);
        editPresenter.editOneArtist(artistId, updatedArtist);

        Intent intent = new Intent(this, ArtistsListView.class);
        startActivity(intent);
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