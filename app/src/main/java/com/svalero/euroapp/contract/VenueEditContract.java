package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Venue;

public interface VenueEditContract {

    interface Model {
        void loadEditOneVenue(long venueId, Venue venue, VenueEditContract.Model.OnLoadEditOneVenueListener listener);
        interface OnLoadEditOneVenueListener{
            void onLoadEditOneVenueSuccess();
            void onLoadEditOneVenueError(String message);
        }
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void editOneVenue(long venueId, Venue venue);
    }
}
