package com.svalero.euroapp.contract;

import com.svalero.euroapp.domain.FavArtist;

import java.util.List;

public interface FavArtistListContract {

    interface Model {
        interface OnLoadFavArtistListener{
            void onLoadFavArtistSuccess(List<FavArtist> favArtists);
            void onLoadFavArtistError(String message);
        }

        void loadAllFavArtist(OnLoadFavArtistListener listener);
    }

    interface View {
        void listFavArtist(List<FavArtist> favArtists);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllFavArtist();
    }



}
