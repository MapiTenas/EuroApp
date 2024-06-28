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
import com.svalero.euroapp.contract.ArtistRegisterContract;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.presenter.ArtistRegisterPresenter;

public class ArtistRegisterView extends AppCompatActivity implements ArtistRegisterContract.View {

    private ArtistRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_register_view);

        presenter = new ArtistRegisterPresenter(this);
    }

    public void addArtist(View view){
        EditText etArtistName = findViewById(R.id.artist_name);
        EditText etArtistBirthDate = findViewById(R.id.artist_birthdate);
        EditText etArtistOriginCountry = findViewById(R.id.artist_originCountry);
        EditText etArtistPublishedCds = findViewById(R.id.artist_publishedCds);
        CheckBox activeArtist = findViewById(R.id.active);
        EditText etArtistInstagram = findViewById(R.id.artist_igfollowers);

        String name = etArtistName.getText().toString();
        String birthDate = etArtistBirthDate.getText().toString();
        String originCountry = etArtistOriginCountry.getText().toString();
        String publishedCdsStr = etArtistPublishedCds.getText().toString();
        boolean active = activeArtist.isChecked();
        String igFollowersStr = etArtistInstagram.getText().toString();

        if(name.isEmpty() || birthDate.isEmpty() || originCountry.isEmpty() || publishedCdsStr.isEmpty() || igFollowersStr.isEmpty()) {
            Toast.makeText(this, R.string.completa_campos, Toast.LENGTH_LONG).show();
            return;
        }

        int publishedCds;
        try {
            publishedCds = Integer.parseInt(publishedCdsStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.cd_valido, Toast.LENGTH_LONG).show();
            return;
        }

        double igFollowers;
        try {
            igFollowers = Double.parseDouble(igFollowersStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.followers_valido, Toast.LENGTH_LONG).show();
            return;
        }

        Artist artist = new Artist(0, name, birthDate, originCountry,publishedCds, active, igFollowers);
        presenter.registerArtist(artist);
        Intent intent = new Intent(this, ArtistsListView.class);
        startActivity(intent);



    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
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