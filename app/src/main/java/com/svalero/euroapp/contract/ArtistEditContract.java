package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Artist;

public interface ArtistEditContract {

    interface Model {
        interface OnLoadEditOneArtistListener{
            void onLoadEditOneArtistSuccess();
            void onLoadEditOneArtistError(String message);
        }
        void loadEditOneArtist(long artistId, Artist artist, ArtistEditContract.Model.OnLoadEditOneArtistListener listener);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }
    interface Presenter{
        void editOneArtist(long artistId, Artist artist);
    }
}
