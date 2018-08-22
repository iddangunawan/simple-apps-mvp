package com.mylektop.simpleappsmvp.post;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mylektop.simpleappsmvp.BaseApp;
import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;
import com.mylektop.simpleappsmvp.networking.Service;

import java.util.List;

import javax.inject.Inject;

public class PostDetailActivity extends BaseApp implements PostView {

    @Inject
    public Service service;
    private TextView tvTitle, tvBody;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        PostPresenter presenter = new PostPresenter(service, this);
        presenter.getPostDetail(getIntent().getIntExtra("POST_ID", 0));
    }

    public void renderView() {
        setContentView(R.layout.activity_post_detail);

        tvTitle = (TextView) findViewById(R.id.postTitle);
        tvBody = (TextView) findViewById(R.id.postBody);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init() {

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
    public void getPostListSuccess(List<PostListDataResponse> postListDataResponses) {

    }

    @Override
    public void getPostDetailSuccess(PostListDataResponse postListDataResponse) {
        tvTitle.setText(postListDataResponse.getTitle());
        tvBody.setText(postListDataResponse.getBody());
    }
}
