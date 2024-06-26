package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.Artist;

public interface ArtistRegisterContract {

    interface Model{
        interface OnRegisterArtistListener{
            void onRegisterArtistSuccess();
            void onRegisterArtistError(String message);
        }
        void registerArtist(OnRegisterArtistListener listener, Artist artist);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void registerArtist(Artist artist);
    }
}
