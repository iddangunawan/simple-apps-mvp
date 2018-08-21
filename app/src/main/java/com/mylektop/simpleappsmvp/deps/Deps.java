package com.mylektop.simpleappsmvp.deps;

import com.mylektop.simpleappsmvp.home.HomeActivity;
import com.mylektop.simpleappsmvp.networking.NetworkModule;
import com.mylektop.simpleappsmvp.post.PostActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MyLektop on 20/08/2018.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);

    void inject(PostActivity postActivity);
}
