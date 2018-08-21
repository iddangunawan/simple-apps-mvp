package com.mylektop.simpleappsmvp.home;

import com.mylektop.simpleappsmvp.models.CityListResponse;

/**
 * Created by MyLektop on 20/08/2018.
 */
public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CityListResponse cityListResponse);
}
