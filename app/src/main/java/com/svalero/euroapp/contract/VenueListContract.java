package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Venue;

import java.util.List;

public interface VenueListContract {

    interface Model {
        interface OnLoadVenueListener {
            void onLoadVenueSuccess(List<Venue> venue);
            void onLoadVenueError(String message);
        }
         void loadAllVenues(OnLoadVenueListener listener);
    }

    interface View {
        void listVenues(List<Venue> venue);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllVenues();
    }
}
