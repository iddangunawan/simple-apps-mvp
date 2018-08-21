package com.mylektop.simpleappsmvp.post;

import com.mylektop.simpleappsmvp.models.PostListDataResponse;

import java.util.List;

/**
 * Created by MyLektop on 21/08/2018.
 */
public interface PostView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(List<PostListDataResponse> postListDataResponses);
}
