package com.svalero.euroapp.contract;


import com.svalero.euroapp.domain.Artist;

public interface ArtistDetailsContract {
    interface Model {
        interface OnLoadOneArtistListener {
            void onLoadOneArtistSuccess(Artist artist);
            void onLoadOneArtistError(String message);
        }
        void loadOneArtist(long artistId, OnLoadOneArtistListener listener);

    }
    interface View {
        void listOneArtist(Artist artist);

        void showMessage(String message);

    }

    interface Presenter {
        void loadOneArtist(long artistId);

    }
}
