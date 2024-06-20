package com.svalero.euroapp.contract;

public interface VenueDeleteContract {

    interface Model {
        interface OnLoadDeleteOneVenueListener {
            void onLoadDeleteOneVenueSuccess();
            void onLoadDeleteOneVenueError(String message);
        }
        void loadDeleteOneVenue(long venueId, OnLoadDeleteOneVenueListener listener);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void deleteOneVenue(long venueId);
    }

}
