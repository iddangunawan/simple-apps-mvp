package com.mylektop.simpleappsmvp.post;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mylektop.simpleappsmvp.BaseApp;
import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.PostListDataResponse;
import com.mylektop.simpleappsmvp.networking.Service;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MyLektop on 21/08/2018.
 */
public class PostActivity extends BaseApp implements PostView {

    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        setContentView(R.layout.activity_post);
        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        list.setLayoutManager(new LinearLayoutManager(this));

        PostPresenter presenter = new PostPresenter(service, this);
        presenter.getPostList();
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(List<PostListDataResponse> postListDataResponses) {
        PostAdapter adapter = new PostAdapter(getApplicationContext(), postListDataResponses,
                new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(PostListDataResponse item) {
                        detail(item.getId());
                    }
                });

        list.setAdapter(adapter);
    }

    private void detail(int post_id) {
        Toast.makeText(getApplicationContext(), "Post ID " + post_id, Toast.LENGTH_LONG).show();
    }
}
