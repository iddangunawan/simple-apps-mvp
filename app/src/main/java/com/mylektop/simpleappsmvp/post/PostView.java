package com.mylektop.simpleappsmvp.post;

import com.mylektop.simpleappsmvp.models.post.PostCommentListResponse;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;

import java.util.List;

/**
 * Created by MyLektop on 21/08/2018.
 */
public interface PostView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getPostListSuccess(List<PostListDataResponse> postListDataResponses);

    void getPostDetailSuccess(PostListDataResponse postListDataResponse);

    void getPostCommentSuccess(List<PostCommentListResponse> postCommentListResponses);
}
