package com.mylektop.simpleappsmvp.networking;

import com.mylektop.simpleappsmvp.models.CityListResponse;
import com.mylektop.simpleappsmvp.models.post.PostCommentListResponse;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by MyLektop on 19/08/2018.
 */
public interface NetworkService {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();

    @GET("posts")
    Observable<List<PostListDataResponse>> getPostList();

    @GET("posts/{id}")
    Observable<PostListDataResponse> getPostDetail(@Path("id") int postId);

    @GET("posts/{id}/comments")
    Observable<List<PostCommentListResponse>> getPostComment(@Path("id") int postId);
}
