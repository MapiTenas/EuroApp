package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Venue;

public interface VenueRegisterContract {
    interface Model {
        interface OnRegisterVenueListener{
            void onRegisterVenueSuccess();
            void onRegisterVenueError(String message);
        }

        void registerVenue(OnRegisterVenueListener listener, Venue venue);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);

    }

    interface Presenter {
        void registerVenue(Venue venue);

    }
}
