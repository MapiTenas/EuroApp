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
import com.svalero.euroapp.domain.Venue;
import com.svalero.euroapp.view.VenueDetailsView;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueHolder> {

    private List<Venue> venue;

    public VenueAdapter(List<Venue> venue){
        this.venue = venue;
    }

    @NonNull
    @Override
    public VenueHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venue_list_item, parent, false);
        return new VenueHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueAdapter.VenueHolder holder, int position) {
        Venue venue = this.venue.get(position);

        holder.tvVenueName.setText(venue.getVenueName());
        holder.tvCity.setText(venue.getCity());
        holder.venueId.setText(String.valueOf(venue.getId()));
    }

    @Override
    public int getItemCount(){
        return venue.size();
    }

    public class VenueHolder extends RecyclerView.ViewHolder{
        public TextView tvVenueName;
        public TextView tvCity;
        public Button detailsButton;
        public TextView venueId;
        public View parentView;


        public VenueHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvVenueName = view.findViewById(R.id.venue_item_venueName);
            tvCity = view.findViewById(R.id.venue_item_city);
            detailsButton = view.findViewById(R.id.see_details_venue_button);
            venueId = view.findViewById(R.id.venue_item_id);
            detailsButton.setOnClickListener(v -> seeVenueDetails(view));

        }
    }

    public void seeVenueDetails (View itemView) {
        long venueId = Long.parseLong(((TextView) itemView.findViewById(R.id.venue_item_id)).getText().toString());
        Intent intent = new Intent(itemView.getContext(), VenueDetailsView.class);
        intent.putExtra("venue_item_id", venueId);
        itemView.getContext().startActivity(intent);

    }

}
