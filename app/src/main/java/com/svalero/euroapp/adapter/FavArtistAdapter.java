package com.svalero.euroapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.euroapp.R;
import com.svalero.euroapp.contract.VenueEditContract;
import com.svalero.euroapp.domain.FavArtist;

import java.util.List;

public class FavArtistAdapter extends RecyclerView.Adapter<FavArtistAdapter.FavArtistHolder> {
    private List<FavArtist> favArtists;

    public FavArtistAdapter(List<FavArtist> favArtists){
        this.favArtists = favArtists;
    }

    @NonNull
    @Override
    public FavArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_artist_item, parent, false);
        return new FavArtistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavArtistAdapter.FavArtistHolder holder, int position){
        FavArtist favArtist = favArtists.get(position);

        holder.tvFavArtistName.setText(favArtist.getName());
        holder.getTvFavArtistCountry.setText(favArtist.getOriginCountry());
    }


    @Override
    public int getItemCount() {
        return favArtists.size();
    }
    public class FavArtistHolder extends RecyclerView.ViewHolder {
        public TextView tvFavArtistName;
        public TextView getTvFavArtistCountry;
        public View parentView;

        public FavArtistHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvFavArtistName = view.findViewById(R.id.favartist_item_name);
            getTvFavArtistCountry = view.findViewById(R.id.favartist_item_country);
        }
    }
}
