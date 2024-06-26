package com.svalero.euroapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.euroapp.R;
import com.svalero.euroapp.domain.Artist;
import com.svalero.euroapp.view.ArtistDetailsView;


import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {

    private List<Artist> artist;

    public ArtistAdapter(List<Artist> artist){

        this.artist = artist;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_list_item, parent, false);
        return new ArtistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder, int position) {
        Artist artist = this.artist.get(position);

        holder.tvArtistName.setText(artist.getName());
        holder.tvCountry.setText(artist.getOriginCountry());
        holder.artistId.setText(String.valueOf(artist.getId()));
    }

    @Override
    public int getItemCount(){
        return artist.size();
    }

    public class ArtistHolder extends RecyclerView.ViewHolder{
        public TextView tvArtistName;
        public TextView tvCountry;
        public Button detailsButton;
        public TextView artistId;
        public View parentView;


        public ArtistHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvArtistName = view.findViewById(R.id.artist_item_name);
            tvCountry = view.findViewById(R.id.artist_item_originCountry);
            detailsButton = view.findViewById(R.id.see_details_artist_button);
            artistId = view.findViewById(R.id.artist_item_id);
            detailsButton.setOnClickListener(v -> seeArtistDetails(view));

        }
    }

    public void seeArtistDetails (View itemView) {
        long artistId = Long.parseLong(((TextView) itemView.findViewById(R.id.artist_item_id)).getText().toString());
        Intent intent = new Intent(itemView.getContext(), ArtistDetailsView.class);
        intent.putExtra("artist_item_id", artistId);
        itemView.getContext().startActivity(intent);

    }
}
