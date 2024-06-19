package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Venue;

public interface VenueDetailsContract {
    interface Model {
        interface OnLoadOneVenueListener {
            void onLoadOneVenueSuccess(Venue venue);
            void onLoadOneVenueError(String message);
        }
        void loadOneVenue(long venueId, OnLoadOneVenueListener listener);

    }
    interface View {
        void listOneVenue(Venue venue);

        void showMessage(String message);

    }

    interface Presenter {
        void loadOneVenue(long venueId);

    }
}
