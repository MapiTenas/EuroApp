package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Artist;

import java.util.List;

public interface ArtistListContract {

    interface Model {
        interface OnLoadArtistListener {
            void onLoadArtistSuccess(List<Artist> artist);
            void onLoadArtistError(String message);
        }
        void loadAllArtists(OnLoadArtistListener listener);
    }

    interface View {
        void listArtists(List<Artist> artist);
        void showMessage(String message);

    }

    interface Presenter {
        void loadAllArtists();
    }
}
