package com.mylektop.simpleappsmvp.networking;

import com.mylektop.simpleappsmvp.models.CityListResponse;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by MyLektop on 19/08/2018.
 */
public class Service {

    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback) {
        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);
                    }
                });

    }

    public interface GetCityListCallback {
        void onSuccess(CityListResponse cityListResponse);

        void onError(NetworkError networkError);
    }

    public Subscription getPostList(final GetPostListCallback callback) {
        return networkService.getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<PostListDataResponse>>>() {
                    @Override
                    public Observable<? extends List<PostListDataResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<PostListDataResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(List<PostListDataResponse> postListDataResponses) {
                        callback.onSuccess(postListDataResponses);
                    }
                });
    }

    public interface GetPostListCallback {
        void onSuccess(List<PostListDataResponse> postListDataResponses);

        void onError(NetworkError networkError);
    }

    public Subscription getPostDetail(final GetPostDetailCallback callback, int postId) {
        return networkService.getPostDetail(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PostListDataResponse>>() {
                    @Override
                    public Observable<? extends PostListDataResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PostListDataResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(PostListDataResponse postListDataResponse) {
                        callback.onSuccess(postListDataResponse);
                    }
                });
    }

    public interface GetPostDetailCallback {
        void onSuccess(PostListDataResponse postListDataResponse);

        void onError(NetworkError networkError);
    }
}
