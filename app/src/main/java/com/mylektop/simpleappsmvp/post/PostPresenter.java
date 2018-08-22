package com.mylektop.simpleappsmvp.post;

import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;
import com.mylektop.simpleappsmvp.networking.NetworkError;
import com.mylektop.simpleappsmvp.networking.Service;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MyLektop on 21/08/2018.
 */
public class PostPresenter {
    private final Service service;
    private final PostView view;
    private CompositeSubscription subscriptions;

    public PostPresenter(Service service, PostView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getPostList() {
        view.showWait();

        Subscription subscription = service.getPostList(new Service.GetPostListCallback() {
            @Override
            public void onSuccess(List<PostListDataResponse> postListDataResponses) {
                view.removeWait();
                view.getPostListSuccess(postListDataResponses);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void getPostDetail(int postId) {
        view.showWait();

        Subscription subscription = service.getPostDetail(new Service.GetPostDetailCallback() {
            @Override
            public void onSuccess(PostListDataResponse postListDataResponse) {
                view.removeWait();
                view.getPostDetailSuccess(postListDataResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }
        }, postId);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
