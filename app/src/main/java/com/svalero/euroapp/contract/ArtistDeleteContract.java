package com.svalero.euroapp.contract;

public interface ArtistDeleteContract {

    interface Model {
        interface OnLoadDeleteOneArtistListener {
            void onLoadDeleteOneArtistSuccess();
            void onLoadDeleteOneArtistError(String message);
        }

        void loadDeleteOneArtist(long artistId, OnLoadDeleteOneArtistListener listener);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void deleteOneArtist(long artistId);
    }
}
