package com.mylektop.simpleappsmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mylektop.simpleappsmvp.deps.DaggerDeps;
import com.mylektop.simpleappsmvp.deps.Deps;
import com.mylektop.simpleappsmvp.networking.NetworkModule;

import java.io.File;

/**
 * Created by MyLektop on 20/08/2018.
 */
public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "response");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
